package com.back.alquiler.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Contrato;
import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Renta;
import com.back.alquiler.repo.ContratoRepo;
import com.back.alquiler.repo.InquilinoRepo;
import com.back.alquiler.repo.RentaRepo;
import com.back.alquiler.repo.SolicitudPropiedadRepo;
import com.back.alquiler.service.ContratoService;
import com.back.alquiler.utils.Constantes;
import com.back.alquiler.utils.ConvertirNumeroALetras;
import com.back.alquiler.utils.WordConstante;
import com.back.alquiler.utils.WordFunctions;

@Service
public class ContratoServiceImpl implements ContratoService {

	@Autowired
	ContratoRepo repoContrato;

	@Autowired
	InquilinoRepo repoInquilino;

	@Autowired
	SolicitudPropiedadRepo repoSolicitudPro;

	@Autowired
	RentaRepo repoRenta;

	private FileOutputStream out;
	private FileInputStream fis;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Contrato registrar(Contrato obj) {
		return repoContrato.save(obj);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Contrato modificar(Contrato obj) {
		return repoContrato.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Contrato> listar() {
		return repoContrato.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoContrato.existsById(id)) {
			repoContrato.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Contrato habilitarContrato(Inquilino inquilino) {
		inquilino.setContratoHecho(true);
		Contrato contrato = new Contrato();
		Inquilino resp = repoInquilino.save(inquilino);
		contrato.setArrendero(inquilino.getArrendero());
		contrato.setCaduco(false);
		contrato.setFechaEmision(new Date());
		contrato.setInquilino(resp);
		contrato.setTiempoContrato(repoSolicitudPro
				.findByArrendatarioAndPropiedadOrderByFechaSolicitudDesc(resp.getArrendatario(), resp.getPropiedad())
				.getTiempoArrendamiento());
		contrato.setGarantia(repoSolicitudPro
				.findByArrendatarioAndPropiedadOrderByFechaSolicitudDesc(resp.getArrendatario(), resp.getPropiedad())
				.getGarantiaPropuesta());
		contrato.setFechaInicio(inquilino.getFechaInicio());
		LocalDate fechaIni = Instant.ofEpochMilli(contrato.getFechaInicio().getTime()).atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate fechaFin = fechaIni.plusMonths(contrato.getTiempoContrato());
		contrato.setFechaFin(java.sql.Date.valueOf(fechaFin));
		for (int i = 1; i <= contrato.getTiempoContrato(); i++) {
			LocalDate fechaInicio = Instant.ofEpochMilli(contrato.getFechaInicio().getTime())
					.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate fechaMesProx = fechaInicio.plusMonths(i);
			Renta renta = new Renta();
			renta.setCantidad(resp.getPropiedad().getCondicionPago().getPrecio());
			renta.setEstado(0);
			renta.setFechaFinRenta(java.sql.Date.valueOf(fechaMesProx));
			fechaInicio = fechaMesProx.minusMonths(1);
			renta.setFechaIniRenta(java.sql.Date.valueOf(fechaInicio));
			renta.setImporteAtrasado(0.0);
			renta.setInquilino(resp);
			renta.setEnvioPago(2);
			renta.setMontoAcumuladoCancelado(0.0);
			repoRenta.save(renta);
		}
		return repoContrato.save(contrato);

	}

	@Override
	public String crearContrato(Inquilino inquilino) throws Exception {
		Contrato contrato;
		contrato = repoContrato.findByInquilinoAndCaduco(inquilino, false);
		String directorio = System.getProperty("user.dir");
		String separador = System.getProperty("file.separator");
		String randomUIID = UUID.randomUUID().toString();
		String ruta = directorio + separador + Constantes.RUTA_CONTRATO + separador + randomUIID + ".docx";
		File file = new File(ruta);
		if (!file.createNewFile()) {
			throw new IOException("No se pudo crear el contrato, la ruta no existe");
		}

		ConvertirNumeroALetras convertidor = new ConvertirNumeroALetras();
		XWPFDocument documento = new XWPFDocument();
		try {
			out = new FileOutputStream(new File(ruta));
			WordConstante constante = new WordConstante();
			String title = constante.titulo().getText1();
			String prfA1 = constante.parrafo1().getText1();
			String prfA2 = constante.parrafo1().getText2();
			String prfA3 = constante.parrafo1().getText3()
					.replace("$arrendero_nombres$", inquilino.getArrendero().getUsuario().getNombres())
					.replace("$arrendero_apellidos$", inquilino.getArrendero().getUsuario().getApellidos());
			String prfA4 = constante.parrafo1().getText4().replace("$dni_arrendero$",
					inquilino.getArrendero().getUsuario().getDni());
			String prfA5 = constante.parrafo1().getText5().replace("$direccion_arrendero$",
					inquilino.getArrendero().getDireccionActual() + ","
							+ inquilino.getArrendero().getDistrito().getDescripcion() + " - "
							+ inquilino.getArrendero().getDepartamento().getDescripcion());
			String prfA6 = constante.parrafo1().getText6();
			String prfA7 = constante.parrafo1().getText7()
					.replace("$arrendatario_nombre$", inquilino.getArrendatario().getUsuario().getNombres())
					.replace("$arrendatario_apellidos$", inquilino.getArrendatario().getUsuario().getApellidos())
					.replace("$dni_arrendatario$", inquilino.getArrendatario().getUsuario().getDni())
					.replace("$direccion_arrendatario$", inquilino.getArrendatario().getDireccionTemporal());

			String subtitleAntece = constante.subtituloAntecedente().getText1();

			String prfB1 = constante.parrafo2().getText1();
			String prfB2 = constante.parrafo2().getText2()
					.replace("$direccion_propiedad$", inquilino.getPropiedad().getDireccion())
					.replace("$nro_partida_registral$", inquilino.getPropiedad().getNroPartida().toString());

			String prfC1 = constante.parrafo3().getText1();
			String prfC2 = constante.parrafo3().getText2();

			String subtitleContrat = constante.subtituloObjContrato().getText1();

			String prfD1 = constante.parrafo4().getText1();
			String prfD2 = constante.parrafo4().getText2();
			String prfD3 = constante.parrafo4().getText3();
			String prfD4 = constante.parrafo4().getText4();
			String prfD5 = constante.parrafo4().getText5();
			String prfD6 = constante.parrafo4().getText6();
			String prfD7 = constante.parrafo4().getText7();
			String prfD8 = constante.parrafo4().getText8();
			String prfD9 = constante.parrafo4().getText9();
			String prfD10 = constante.parrafo4().getText10();

			String subtitleOpPago = constante.subituloFormaOportunidad().getText1();

			String prfE1 = constante.parrafo5().getText1();
			String prfE2 = constante.parrafo5().getText2();
			String prfE3 = constante.parrafo5().getText3();
			String prfE4 = constante.parrafo5().getText4()
					.replace("$monto_renta$", inquilino.getPropiedad().getCondicionPago().getPrecio().toString())
					.replace("$monto_renta_letras$",
							convertidor.convertir(inquilino.getPropiedad().getCondicionPago().getPrecio() + "", true));

			String prfF1 = constante.parrafo6().getText1();
			String prfF2 = constante.parrafo6().getText2();
			String prfF3 = constante.parrafo6().getText3();
			String prfF4 = constante.parrafo6().getText4();
			String prfF5 = constante.parrafo6().getText5()
					.replace("$monto_garantia$", contrato.getGarantia().toString())
					.replace("$monto_garantia_letras$", convertidor.convertir(contrato.getGarantia() + "", true));
			String prfF6 = constante.parrafo6().getText6();

			String prfG1 = constante.parrafo7().getText1();
			String prfG2 = constante.parrafo7().getText2();
			String prfG3 = constante.parrafo7().getText3();
			String prfG4 = constante.parrafo7().getText4();
			String prfG5 = constante.parrafo7().getText5();
			String prfG6 = constante.parrafo7().getText6().replace("$dia_pago_mes$",
					inquilino.getPropiedad().getCondicionPago().getDiaMesCobro().toString());

			String subtitlePlazoContrat = constante.subtituloPlazoContrato().getText1();

			String prfH1 = constante.parrafo8().getText1();
			String prfH2 = constante.parrafo8().getText2()
					.replace("$tiempo_contrato$", contrato.getTiempoContrato().toString())
					.replace("$fecha_contrato_inicio$", contrato.getFechaInicio().toString())
					.replace("$fecha_contrato_fin$", contrato.getFechaFin().toString());

			String subtitleObligacion = constante.subtituloObligaciones().getText1();

			String prfI1 = constante.parrafo9().getText1();
			String prfI2 = constante.parrafo9().getText2();

			String prfJ1 = constante.parrafo10().getText1();
			String prfJ2 = constante.parrafo10().getText2();

			String prfK1 = constante.parrafo11().getText1();
			String prfK2 = constante.parrafo11().getText2();
			String prfK3 = constante.parrafo11().getText3();
			String prfK4 = constante.parrafo11().getText4();

			String prfL1Arrendatario = constante.parrafo12ReparacionArrendatario().getText1();
			String prfL2Arrendatario = constante.parrafo12ReparacionArrendatario().getText2();

			String prfL1Arrendador = constante.parrafo12ReparacionArrendero().getText1();
			String prfL2Arrendador = constante.parrafo12ReparacionArrendero().getText2();

			String prfM1 = constante.parrafo13().getText1();
			String prfM2 = constante.parrafo13().getText2();
			String prfM3 = constante.parrafo13().getText3();
			String prfM4 = constante.parrafo13().getText4();

			String prfN1 = constante.parrafo14().getText1();
			String prfN2 = constante.parrafo14().getText2();

			String subtitleClausPena = constante.subtituloClausulaPena().getText1();

			String prfO1 = constante.parrafo15().getText1();
			String prfO2 = constante.parrafo15().getText2();
			String prfO3 = constante.parrafo15().getText3();
			String prfO4 = constante.parrafo15().getText4()
					.replace("$penalidad$",
							inquilino.getPropiedad().getCondicionPago().getPenalidadNoDesocupar().toString())
					.replace("$penalidad_letras$", convertidor.convertir(
							inquilino.getPropiedad().getCondicionPago().getPenalidadNoDesocupar() + "", true));

			String subtitleClausuGarant = constante.subtituloClausulaGarantia().getText1();

			String prfP1 = constante.parrafo16().getText1();
			String prfP2 = constante.parrafo16().getText2();
			String prfP3 = constante.parrafo16().getText3();
			String prfP4 = constante.parrafo16().getText4();

			String subtitleClausuSolConflict = constante.subtituloClausulaSolucionConflicto().getText1();

			String prfQ1 = constante.parrafo17().getText1();
			String prfQ2 = constante.parrafo17().getText2();
			String prfQ3 = constante.parrafo17().getText3();
			String prfQ4 = constante.parrafo17().getText4();

			String firma = constante.firma().getText1();

			XWPFParagraph titulo = documento.createParagraph();
			titulo.setAlignment(ParagraphAlignment.CENTER);
			titulo.setVerticalAlignment(TextAlignment.TOP);
			XWPFRun run = titulo.createRun();
			WordFunctions.setearTitulo(run, title);

			XWPFParagraph parrafoA = documento.createParagraph();
			parrafoA.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aA = parrafoA.createRun();
			WordFunctions.setearParrafoNormal(aA,prfA1);
			XWPFRun bA = parrafoA.createRun();
			WordFunctions.setearParrafoNegrita(bA,prfA2);
			XWPFRun cA = parrafoA.createRun();
			WordFunctions.setearParrafoNormal(cA,prfA3);
			XWPFRun dA = parrafoA.createRun();
			WordFunctions.setearParrafoNegrita(dA,prfA4);
			XWPFRun eA = parrafoA.createRun();
			WordFunctions.setearParrafoNormal(eA,prfA5);
			XWPFRun fA = parrafoA.createRun();
			WordFunctions.setearParrafoNegrita(fA,prfA6);
			XWPFRun gA = parrafoA.createRun();
			WordFunctions.setearParrafoNormal(gA,prfA7);
			gA.addBreak();

			XWPFParagraph subtituloAntecedente = documento.createParagraph();
			subtituloAntecedente.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun subAnte = subtituloAntecedente.createRun();
			WordFunctions.setearParrafoSubrayadoYNegrita(subAnte, subtitleAntece);

			XWPFParagraph parrafoB = documento.createParagraph();
			parrafoB.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aB = parrafoB.createRun();
			WordFunctions.setearParrafoNegrita(aB,prfB1);
			XWPFRun bB = parrafoB.createRun();
			WordFunctions.setearParrafoNormal(bB,prfB2);

			XWPFParagraph parrafoC = documento.createParagraph();
			parrafoC.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aC = parrafoC.createRun();
			WordFunctions.setearParrafoNegrita(aC,prfC1);
			XWPFRun bC = parrafoC.createRun();
			WordFunctions.setearParrafoNormal(bC,prfC2);
			bC.addBreak();

			XWPFParagraph subtituloContrato = documento.createParagraph();
			subtituloContrato.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun subCont = subtituloContrato.createRun();
			WordFunctions.setearParrafoSubrayadoYNegrita(subCont, subtitleContrat);

			XWPFParagraph parrafoD = documento.createParagraph();
			parrafoD.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aD = parrafoD.createRun();
			WordFunctions.setearParrafoNegrita(aD,prfD1);
			XWPFRun bD = parrafoD.createRun();
			WordFunctions.setearParrafoNormal(bD,prfD2);
			XWPFRun cD = parrafoD.createRun();
			WordFunctions.setearParrafoNegrita(cD,prfD3);
			XWPFRun dD = parrafoD.createRun();
			WordFunctions.setearParrafoNormal(dD,prfD4);
			XWPFRun eD = parrafoD.createRun();
			WordFunctions.setearParrafoNegrita(eD,prfD5);
			XWPFRun fD = parrafoD.createRun();
			WordFunctions.setearParrafoNormal(fD,prfD6);
			XWPFRun gD = parrafoD.createRun();
			WordFunctions.setearParrafoNegrita(gD,prfD7);
			XWPFRun hD = parrafoD.createRun();
			WordFunctions.setearParrafoNormal(hD,prfD8);
			XWPFRun iD = parrafoD.createRun();
			WordFunctions.setearParrafoNegrita(iD,prfD9);
			XWPFRun jD = parrafoD.createRun();
			WordFunctions.setearParrafoNormal(jD,prfD10);
			jD.addBreak();

			XWPFParagraph subtituloOportPago = documento.createParagraph();
			subtituloOportPago.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun subOpPago = subtituloOportPago.createRun();
			WordFunctions.setearParrafoSubrayadoYNegrita(subOpPago, subtitleOpPago);

			XWPFParagraph parrafoE = documento.createParagraph();
			parrafoE.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aE = parrafoE.createRun();
			WordFunctions.setearParrafoNegrita(aE,prfE1);
			XWPFRun bE = parrafoE.createRun();
			WordFunctions.setearParrafoNormal(bE,prfE2);
			XWPFRun cE = parrafoE.createRun();
			WordFunctions.setearParrafoNegrita(cE,prfE3);
			XWPFRun dE = parrafoE.createRun();
			WordFunctions.setearParrafoNormal(dE,prfE4);

			XWPFParagraph parrafoF = documento.createParagraph();
			parrafoF.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aF = parrafoF.createRun();
			WordFunctions.setearParrafoNormal(aF,prfF1);
			XWPFRun bF = parrafoF.createRun();
			WordFunctions.setearParrafoNegrita(bF,prfF2);
			XWPFRun cF = parrafoF.createRun();
			WordFunctions.setearParrafoNormal(cF,prfF3);
			XWPFRun dF = parrafoF.createRun();
			WordFunctions.setearParrafoNegrita(dF,prfF4);
			XWPFRun eF = parrafoF.createRun();
			WordFunctions.setearParrafoNormal(eF,prfF5);
			XWPFRun fF = parrafoF.createRun();
			WordFunctions.setearParrafoNegrita(fF,prfF6);

			XWPFParagraph parrafoG = documento.createParagraph();
			parrafoG.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aG = parrafoG.createRun();
			WordFunctions.setearParrafoNegrita(aG,prfG1);
			XWPFRun bG = parrafoG.createRun();
			WordFunctions.setearParrafoNormal(bG,prfG2);
			XWPFRun cG = parrafoG.createRun();
			WordFunctions.setearParrafoNegrita(cG,prfG3);
			XWPFRun dG = parrafoG.createRun();
			WordFunctions.setearParrafoNormal(dG,prfG4);
			XWPFRun eG = parrafoG.createRun();
			WordFunctions.setearParrafoNegrita(eG,prfG5);
			XWPFRun fG = parrafoG.createRun();
			WordFunctions.setearParrafoNormal(fG,prfG6);
			fG.addBreak();

			XWPFParagraph subtituloPlazoContrato = documento.createParagraph();
			subtituloPlazoContrato.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun subPlzContr = subtituloPlazoContrato.createRun();
			WordFunctions.setearParrafoSubrayadoYNegrita(subPlzContr, subtitlePlazoContrat);

			XWPFParagraph parrafoH = documento.createParagraph();
			parrafoH.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aH = parrafoH.createRun();
			WordFunctions.setearParrafoNegrita(aH,prfH1);
			XWPFRun bH = parrafoH.createRun();
			WordFunctions.setearParrafoNormal(bH,prfH2);
			bH.addBreak();

			XWPFParagraph subtituloOblPartes = documento.createParagraph();
			subtituloOblPartes.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun subOblPart = subtituloOblPartes.createRun();
			WordFunctions.setearParrafoSubrayadoYNegrita(subOblPart, subtitleObligacion);

			XWPFParagraph parrafoI = documento.createParagraph();
			parrafoI.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aI = parrafoI.createRun();
			WordFunctions.setearParrafoNegrita(aI,prfI1);
			XWPFRun bI = parrafoI.createRun();
			WordFunctions.setearParrafoNormal(bI,prfI2);

			XWPFParagraph parrafoJ = documento.createParagraph();
			parrafoJ.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aJ = parrafoJ.createRun();
			WordFunctions.setearParrafoNegrita(aJ,prfJ1);
			XWPFRun bJ = parrafoJ.createRun();
			WordFunctions.setearParrafoNormal(bJ,prfJ2);
			
			XWPFParagraph parrafoK = documento.createParagraph();
			parrafoK.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aK = parrafoK.createRun();
			WordFunctions.setearParrafoNegrita(aK,prfK1);
			XWPFRun bK = parrafoK.createRun();
			WordFunctions.setearParrafoNormal(bK,prfK2);
			XWPFRun cK = parrafoK.createRun();
			WordFunctions.setearParrafoNegrita(cK,prfK3);
			XWPFRun dK = parrafoK.createRun();
			WordFunctions.setearParrafoNormal(dK,prfK4);

			XWPFParagraph parrafoL = documento.createParagraph();
			parrafoL.setAlignment(ParagraphAlignment.BOTH);
			if (inquilino.getPropiedad().getCondicionPago().getResponsabilidadReparar()) {
				XWPFRun aL = parrafoL.createRun();
				WordFunctions.setearParrafoNegrita(aL, prfL1Arrendador);
				XWPFRun bL = parrafoL.createRun();
				WordFunctions.setearParrafoNormal(bL, prfL2Arrendador);
			} else {
				XWPFRun aL = parrafoL.createRun();
				WordFunctions.setearParrafoNegrita(aL, prfL1Arrendatario);
				XWPFRun bL = parrafoL.createRun();
				WordFunctions.setearParrafoNormal(bL, prfL2Arrendatario);
			}

			XWPFParagraph parrafoM = documento.createParagraph();
			parrafoM.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aM = parrafoM.createRun();
			WordFunctions.setearParrafoNegrita(aM, prfM1);
			XWPFRun bM = parrafoM.createRun();
			WordFunctions.setearParrafoNormal(bM, prfM2);
			XWPFRun cM = parrafoM.createRun();
			WordFunctions.setearParrafoNegrita(cM, prfM3);
			XWPFRun dM = parrafoM.createRun();
			WordFunctions.setearParrafoNormal(dM, prfM4);

			XWPFParagraph parrafoN = documento.createParagraph();
			parrafoN.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aN = parrafoN.createRun();
			WordFunctions.setearParrafoNegrita(aN, prfN1);
			XWPFRun bN = parrafoN.createRun();
			WordFunctions.setearParrafoNormal(bN, prfN2);
			bN.addBreak();

			XWPFParagraph subtituloClausuPenal = documento.createParagraph();
			subtituloClausuPenal.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun subClauPen = subtituloClausuPenal.createRun();
			WordFunctions.setearParrafoSubrayadoYNegrita(subClauPen, subtitleClausPena);

			XWPFParagraph parrafoO = documento.createParagraph();
			parrafoO.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aO = parrafoO.createRun();
			WordFunctions.setearParrafoNegrita(aO, prfO1);
			XWPFRun bO = parrafoO.createRun();
			WordFunctions.setearParrafoNormal(bO, prfO2);
			XWPFRun cO = parrafoO.createRun();
			WordFunctions.setearParrafoNegrita(cO, prfO3);
			XWPFRun dO = parrafoO.createRun();
			WordFunctions.setearParrafoNormal(dO, prfO4);
			dO.addBreak();

			XWPFParagraph subtituloClausuGarantia = documento.createParagraph();
			subtituloClausuGarantia.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun subClauGara = subtituloClausuGarantia.createRun();
			WordFunctions.setearParrafoSubrayadoYNegrita(subClauGara, subtitleClausuGarant);

			XWPFParagraph parrafoP = documento.createParagraph();
			parrafoP.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aP = parrafoP.createRun();
			WordFunctions.setearParrafoNegrita(aP, prfP1);
			XWPFRun bP = parrafoP.createRun();
			WordFunctions.setearParrafoNormal(bP, prfP2);
			XWPFRun cP = parrafoP.createRun();
			WordFunctions.setearParrafoNegrita(cP, prfP3);
			XWPFRun dP = parrafoP.createRun();
			WordFunctions.setearParrafoNormal(dP, prfP4);
			dP.addBreak();

			XWPFParagraph subtituloClausuConflicto = documento.createParagraph();
			subtituloClausuConflicto.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun subClauConf = subtituloClausuConflicto.createRun();
			WordFunctions.setearParrafoSubrayadoYNegrita(subClauConf, subtitleClausuSolConflict);
	
			XWPFParagraph parrafoQ = documento.createParagraph();
			parrafoQ.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aQ = parrafoQ.createRun();
			WordFunctions.setearParrafoNegrita(aQ, prfQ1);
			XWPFRun bQ = parrafoQ.createRun();
			WordFunctions.setearParrafoNormal(bQ, prfQ2);
			XWPFRun cQ = parrafoQ.createRun();
			WordFunctions.setearParrafoNegrita(cQ, prfQ3);
			XWPFRun dQ = parrafoQ.createRun();
			WordFunctions.setearParrafoNormal(dQ, prfQ4);
			dQ.addBreak();
			dQ.addBreak();
			dQ.addBreak();
			dQ.addBreak();
			dQ.addBreak();
			dQ.addBreak();
			dQ.addBreak();
			dQ.addBreak();

			XWPFParagraph parrafoFirma = documento.createParagraph();
			parrafoFirma.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun aFirma = parrafoFirma.createRun();
			WordFunctions.setearParrafoNegrita(aFirma, firma);
			documento.write(out);

		} catch (Exception e) {
			throw e;
		} finally {
			documento.close();
			out.close();
		}
		return randomUIID;

	}

	@Override
	public byte[] obtenerContrato(Integer id) throws Exception {
		byte[] bArray;
		try {
			Optional<Contrato> op = repoContrato.findById(id);
			Contrato contrato = op.isPresent() ? op.get() : new Contrato();
			String directorio = System.getProperty("user.dir");
			String separador = System.getProperty("file.separator");
			String ruta = directorio + separador + Constantes.RUTA_CONTRATO + separador + contrato.getArchivoContrato()
					+ ".docx";
			File archivo = new File(ruta);
			fis = new FileInputStream(archivo);
			bArray = new byte[(int) archivo.length()];
			while (fis.read(bArray) > 0) {
			}
		} catch (Exception e) {
			throw e;
		} finally {
			fis.close();
		}
		return bArray;

	}

	@Override
	public List<Contrato> listarPorArrendero(Integer id) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		return repoContrato.findByArrendero(arrendero);
	}

}
