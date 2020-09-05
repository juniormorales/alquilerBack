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
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
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

@Service
public class ContratoServiceImpl implements ContratoService {

	@Autowired
	ContratoRepo repo_contrato;

	@Autowired
	InquilinoRepo repo_inquilino;

	@Autowired
	SolicitudPropiedadRepo repo_solicitud_pro;

	@Autowired
	RentaRepo repo_renta;

	private FileOutputStream out;
	private FileInputStream fis;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Contrato registrar(Contrato obj) {
		try {
			return repo_contrato.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Contrato modificar(Contrato obj) {
		try {
			return repo_contrato.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Contrato leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contrato> listar() {
		return repo_contrato.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repo_contrato.existsById(id)) {
			repo_contrato.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Contrato habilitarContrato(Inquilino inquilino) {
		inquilino.setContratoHecho(true);
		Contrato contrato = new Contrato();
		try {
			Inquilino resp = repo_inquilino.save(inquilino);
			contrato.setArrendero(inquilino.getArrendero());
			contrato.setCaduco(false);
			contrato.setFechaEmision(new Date());
			contrato.setInquilino(resp);
			contrato.setTiempoContrato(repo_solicitud_pro.findByArrendatarioAndPropiedadOrderByFechaSolicitudDesc(
					resp.getArrendatario(), resp.getPropiedad()).getTiempoArrendamiento());
			contrato.setGarantia(repo_solicitud_pro.findByArrendatarioAndPropiedadOrderByFechaSolicitudDesc(
					resp.getArrendatario(), resp.getPropiedad()).getGarantiaPropuesta());
			contrato.setFechaInicio(inquilino.getFecha_inicio());
			LocalDate fechaIni = Instant.ofEpochMilli(contrato.getFechaInicio().getTime())
					.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate fechaFin = fechaIni.plusMonths(contrato.getTiempoContrato());
			contrato.setFechaFin(java.sql.Date.valueOf(fechaFin));
			for (int i = 1; i <= contrato.getTiempoContrato(); i++) {
				LocalDate fecha_inicio = Instant.ofEpochMilli(contrato.getFechaInicio().getTime())
						.atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate fecha_mes_prox = fecha_inicio.plusMonths(i);
				Renta renta = new Renta();
				renta.setCantidad(resp.getPropiedad().getCondicionPago().getPrecio());
				renta.setEstado(0);
				renta.setFechaFinRenta(java.sql.Date.valueOf(fecha_mes_prox));
				fecha_inicio = fecha_mes_prox.minusMonths(1);
				renta.setFechaIniRenta(java.sql.Date.valueOf(fecha_inicio));
				renta.setImporteAtrasado(0.0);
				renta.setInquilino(resp);
				renta.setEnvioPago(2);
				renta.setMontoAcumuladoCancelado(0.0);
				repo_renta.save(renta);
			}

			return repo_contrato.save(contrato);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public String crearContrato(Inquilino inquilino) throws Exception {
		Contrato contrato;
		try {
			contrato = repo_contrato.findByInquilinoAndCaduco(inquilino, false);
		} catch (Exception e) {
			throw e;
		}
		String directorio = System.getProperty("user.dir");
		String separador = System.getProperty("file.separator");
		String randomUIID = UUID.randomUUID().toString();
		String ruta = directorio + separador + Constantes.rutaContrato + separador + randomUIID + ".docx";
		File file = new File(ruta);
		if(!file.createNewFile()) {
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

			String subtitle_antece = constante.subtitulo_antecedente().getText1();

			String prfB1 = constante.parrafo2().getText1();
			String prfB2 = constante.parrafo2().getText2()
					.replace("$direccion_propiedad$", inquilino.getPropiedad().getDireccion())
					.replace("$nro_partida_registral$", inquilino.getPropiedad().getNroPartida().toString());

			String prfC1 = constante.parrafo3().getText1();
			String prfC2 = constante.parrafo3().getText2();

			String subtitle_contrat = constante.subtitulo_obj_contrato().getText1();

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

			String subtitle_op_pago = constante.subitulo_forma_oportunidad().getText1();

			String prfE1 = constante.parrafo5().getText1();
			String prfE2 = constante.parrafo5().getText2();
			String prfE3 = constante.parrafo5().getText3();
			String prfE4 = constante.parrafo5().getText4()
					.replace("$monto_renta$", inquilino.getPropiedad().getCondicionPago().getPrecio().toString())
					.replace("$monto_renta_letras$",
							convertidor.Convertir(inquilino.getPropiedad().getCondicionPago().getPrecio() + "", true));

			String prfF1 = constante.parrafo6().getText1();
			String prfF2 = constante.parrafo6().getText2();
			String prfF3 = constante.parrafo6().getText3();
			String prfF4 = constante.parrafo6().getText4();
			String prfF5 = constante.parrafo6().getText5()
					.replace("$monto_garantia$", contrato.getGarantia().toString())
					.replace("$monto_garantia_letras$", convertidor.Convertir(contrato.getGarantia() + "", true));
			String prfF6 = constante.parrafo6().getText6();

			String prfG1 = constante.parrafo7().getText1();
			String prfG2 = constante.parrafo7().getText2();
			String prfG3 = constante.parrafo7().getText3();
			String prfG4 = constante.parrafo7().getText4();
			String prfG5 = constante.parrafo7().getText5();
			String prfG6 = constante.parrafo7().getText6().replace("$dia_pago_mes$",
					inquilino.getPropiedad().getCondicionPago().getDiaMesCobro().toString());

			String subtitle_plazo_contrat = constante.subtitulo_plazo_contrato().getText1();

			String prfH1 = constante.parrafo8().getText1();
			String prfH2 = constante.parrafo8().getText2()
					.replace("$tiempo_contrato$", contrato.getTiempoContrato().toString())
					.replace("$fecha_contrato_inicio$", contrato.getFechaInicio().toString())
					.replace("$fecha_contrato_fin$", contrato.getFechaFin().toString());

			String subtitle_obligacion = constante.subtitulo_obligaciones().getText1();

			String prfI1 = constante.parrafo9().getText1();
			String prfI2 = constante.parrafo9().getText2();

			String prfJ1 = constante.parrafo10().getText1();
			String prfJ2 = constante.parrafo10().getText2();

			String prfK1 = constante.parrafo11().getText1();
			String prfK2 = constante.parrafo11().getText2();
			String prfK3 = constante.parrafo11().getText3();
			String prfK4 = constante.parrafo11().getText4();

			String prfL1_arrendatario = constante.parrafo12_reparacion_arrendatario().getText1();
			String prfL2_arrendatario = constante.parrafo12_reparacion_arrendatario().getText2();

			String prfL1_arrendador = constante.parrafo12_reparacion_arrendero().getText1();
			String prfL2_arrendador = constante.parrafo12_reparacion_arrendero().getText2();

			String prfM1 = constante.parrafo13().getText1();
			String prfM2 = constante.parrafo13().getText2();
			String prfM3 = constante.parrafo13().getText3();
			String prfM4 = constante.parrafo13().getText4();

			String prfN1 = constante.parrafo14().getText1();
			String prfN2 = constante.parrafo14().getText2();

			String subtitle_claus_pena = constante.subtitulo_clausula_pena().getText1();

			String prfO1 = constante.parrafo15().getText1();
			String prfO2 = constante.parrafo15().getText2();
			String prfO3 = constante.parrafo15().getText3();
			String prfO4 = constante.parrafo15().getText4()
					.replace("$penalidad$",
							inquilino.getPropiedad().getCondicionPago().getPenalidadNoDesocupar().toString())
					.replace("$penalidad_letras$", convertidor.Convertir(
							inquilino.getPropiedad().getCondicionPago().getPenalidadNoDesocupar() + "", true));

			String subtitle_clausu_garant = constante.subtitulo_clausula_garantia().getText1();

			String prfP1 = constante.parrafo16().getText1();
			String prfP2 = constante.parrafo16().getText2();
			String prfP3 = constante.parrafo16().getText3();
			String prfP4 = constante.parrafo16().getText4();

			String subtitle_clausu_sol_conflict = constante.subtitulo_clausula_solucion_conflicto().getText1();

			String prfQ1 = constante.parrafo17().getText1();
			String prfQ2 = constante.parrafo17().getText2();
			String prfQ3 = constante.parrafo17().getText3();
			String prfQ4 = constante.parrafo17().getText4();

			String firma = constante.firma().getText1();

			XWPFParagraph titulo = documento.createParagraph();
			titulo.setAlignment(ParagraphAlignment.CENTER);
			titulo.setVerticalAlignment(TextAlignment.TOP);
			XWPFRun run1 = titulo.createRun();
			run1.setBold(true);
			run1.setText(title);
			run1.setFontFamily("Arial");
			run1.setFontSize(15);
			run1.addBreak();

			XWPFParagraph parrafoA = documento.createParagraph();
			parrafoA.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aA = parrafoA.createRun();
			aA.setText(prfA1);
			aA.setFontFamily("Arial");
			aA.setFontSize(12);
			XWPFRun bA = parrafoA.createRun();
			bA.setText(prfA2);
			bA.setBold(true);
			bA.setFontFamily("Arial");
			bA.setFontSize(12);
			XWPFRun cA = parrafoA.createRun();
			cA.setText(prfA3);
			cA.setFontFamily("Arial");
			cA.setFontSize(12);
			XWPFRun dA = parrafoA.createRun();
			dA.setText(prfA4);
			dA.setBold(true);
			dA.setFontFamily("Arial");
			dA.setFontSize(12);
			XWPFRun eA = parrafoA.createRun();
			eA.setText(prfA5);
			eA.setFontFamily("Arial");
			eA.setFontSize(12);
			XWPFRun fA = parrafoA.createRun();
			fA.setText(prfA6);
			fA.setBold(true);
			fA.setFontFamily("Arial");
			fA.setFontSize(12);
			XWPFRun gA = parrafoA.createRun();
			gA.setText(prfA7);
			gA.setFontFamily("Arial");
			gA.setFontSize(12);
			gA.addBreak();

			XWPFParagraph subtitulo_antecedente = documento.createParagraph();
			subtitulo_antecedente.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun sub_ante = subtitulo_antecedente.createRun();
			sub_ante.setText(subtitle_antece);
			sub_ante.setFontFamily("Arial");
			sub_ante.setFontSize(12);
			sub_ante.setBold(true);
			sub_ante.setUnderline(UnderlinePatterns.SINGLE);

			XWPFParagraph parrafoB = documento.createParagraph();
			parrafoB.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aB = parrafoB.createRun();
			aB.setText(prfB1);
			aB.setBold(true);
			aB.setFontFamily("Arial");
			aB.setFontSize(12);
			XWPFRun bB = parrafoB.createRun();
			bB.setText(prfB2);
			bB.setFontFamily("Arial");
			bB.setFontSize(12);

			XWPFParagraph parrafoC = documento.createParagraph();
			parrafoC.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aC = parrafoC.createRun();
			aC.setText(prfC1);
			aC.setBold(true);
			aC.setFontFamily("Arial");
			aC.setFontSize(12);
			XWPFRun bC = parrafoC.createRun();
			bC.setText(prfC2);
			bC.setFontFamily("Arial");
			bC.setFontSize(12);
			bC.addBreak();

			XWPFParagraph subtitulo_contrato = documento.createParagraph();
			subtitulo_contrato.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun sub_cont = subtitulo_contrato.createRun();
			sub_cont.setText(subtitle_contrat);
			sub_cont.setFontFamily("Arial");
			sub_cont.setFontSize(12);
			sub_cont.setBold(true);
			sub_cont.setUnderline(UnderlinePatterns.SINGLE);

			XWPFParagraph parrafoD = documento.createParagraph();
			parrafoD.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aD = parrafoD.createRun();
			aD.setText(prfD1);
			aD.setBold(true);
			aD.setFontFamily("Arial");
			aD.setFontSize(12);
			XWPFRun bD = parrafoD.createRun();
			bD.setText(prfD2);
			bD.setFontFamily("Arial");
			bD.setFontSize(12);
			XWPFRun cD = parrafoD.createRun();
			cD.setText(prfD3);
			cD.setBold(true);
			cD.setFontFamily("Arial");
			cD.setFontSize(12);
			XWPFRun dD = parrafoD.createRun();
			dD.setText(prfD4);
			dD.setFontFamily("Arial");
			dD.setFontSize(12);
			XWPFRun eD = parrafoD.createRun();
			eD.setText(prfD5);
			eD.setBold(true);
			eD.setFontFamily("Arial");
			eD.setFontSize(12);
			XWPFRun fD = parrafoD.createRun();
			fD.setText(prfD6);
			fD.setFontFamily("Arial");
			fD.setFontSize(12);
			XWPFRun gD = parrafoD.createRun();
			gD.setText(prfD7);
			gD.setBold(true);
			gD.setFontFamily("Arial");
			gD.setFontSize(12);
			XWPFRun hD = parrafoD.createRun();
			hD.setText(prfD8);
			hD.setFontFamily("Arial");
			hD.setFontSize(12);
			XWPFRun iD = parrafoD.createRun();
			iD.setText(prfD9);
			iD.setBold(true);
			iD.setFontFamily("Arial");
			iD.setFontSize(12);
			XWPFRun jD = parrafoD.createRun();
			jD.setText(prfD10);
			jD.setFontFamily("Arial");
			jD.setFontSize(12);
			jD.addBreak();

			XWPFParagraph subtitulo_oport_pago = documento.createParagraph();
			subtitulo_oport_pago.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun sub_op_pago = subtitulo_oport_pago.createRun();
			sub_op_pago.setText(subtitle_op_pago);
			sub_op_pago.setFontFamily("Arial");
			sub_op_pago.setFontSize(12);
			sub_op_pago.setBold(true);
			sub_op_pago.setUnderline(UnderlinePatterns.SINGLE);

			XWPFParagraph parrafoE = documento.createParagraph();
			parrafoE.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aE = parrafoE.createRun();
			aE.setText(prfE1);
			aE.setBold(true);
			aE.setFontFamily("Arial");
			aE.setFontSize(12);
			XWPFRun bE = parrafoE.createRun();
			bE.setText(prfE2);
			bE.setFontFamily("Arial");
			bE.setFontSize(12);
			XWPFRun cE = parrafoE.createRun();
			cE.setText(prfE3);
			cE.setBold(true);
			cE.setFontFamily("Arial");
			cE.setFontSize(12);
			XWPFRun dE = parrafoE.createRun();
			dE.setText(prfE4);
			dE.setFontFamily("Arial");
			dE.setFontSize(12);

			XWPFParagraph parrafoF = documento.createParagraph();
			parrafoF.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aF = parrafoF.createRun();
			aF.setText(prfF1);
			aF.setFontFamily("Arial");
			aF.setFontSize(12);
			XWPFRun bF = parrafoF.createRun();
			bF.setText(prfF2);
			bF.setBold(true);
			bF.setFontFamily("Arial");
			bF.setFontSize(12);
			XWPFRun cF = parrafoF.createRun();
			cF.setText(prfF3);
			cF.setFontFamily("Arial");
			cF.setFontSize(12);
			XWPFRun dF = parrafoF.createRun();
			dF.setText(prfF4);
			dF.setBold(true);
			dF.setFontFamily("Arial");
			dF.setFontSize(12);
			XWPFRun eF = parrafoF.createRun();
			eF.setText(prfF5);
			eF.setFontFamily("Arial");
			eF.setFontSize(12);
			XWPFRun fF = parrafoF.createRun();
			fF.setText(prfF6);
			fF.setBold(true);
			fF.setFontFamily("Arial");
			fF.setFontSize(12);

			XWPFParagraph parrafoG = documento.createParagraph();
			parrafoG.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aG = parrafoG.createRun();
			aG.setText(prfG1);
			aG.setBold(true);
			aG.setFontFamily("Arial");
			aG.setFontSize(12);
			XWPFRun bG = parrafoG.createRun();
			bG.setText(prfG2);
			bG.setFontFamily("Arial");
			bG.setFontSize(12);
			XWPFRun cG = parrafoG.createRun();
			cG.setText(prfG3);
			cG.setBold(true);
			cG.setFontFamily("Arial");
			cG.setFontSize(12);
			XWPFRun dG = parrafoG.createRun();
			dG.setText(prfG4);
			dG.setFontFamily("Arial");
			dG.setFontSize(12);
			XWPFRun eG = parrafoG.createRun();
			eG.setText(prfG5);
			eG.setBold(true);
			eG.setFontFamily("Arial");
			eG.setFontSize(12);
			XWPFRun fG = parrafoG.createRun();
			fG.setText(prfG6);
			fG.setFontFamily("Arial");
			fG.setFontSize(12);
			fG.addBreak();

			XWPFParagraph subtitulo_plazo_contrato = documento.createParagraph();
			subtitulo_plazo_contrato.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun sub_plz_contr = subtitulo_plazo_contrato.createRun();
			sub_plz_contr.setText(subtitle_plazo_contrat);
			sub_plz_contr.setFontFamily("Arial");
			sub_plz_contr.setFontSize(12);
			sub_plz_contr.setBold(true);
			sub_plz_contr.setUnderline(UnderlinePatterns.SINGLE);

			XWPFParagraph parrafoH = documento.createParagraph();
			parrafoH.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aH = parrafoH.createRun();
			aH.setText(prfH1);
			aH.setBold(true);
			aH.setFontFamily("Arial");
			aH.setFontSize(12);
			XWPFRun bH = parrafoH.createRun();
			bH.setText(prfH2);
			bH.setFontFamily("Arial");
			bH.setFontSize(12);
			bH.addBreak();

			XWPFParagraph subtitulo_obl_partes = documento.createParagraph();
			subtitulo_obl_partes.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun sub_obl_part = subtitulo_obl_partes.createRun();
			sub_obl_part.setText(subtitle_obligacion);
			sub_obl_part.setFontFamily("Arial");
			sub_obl_part.setFontSize(12);
			sub_obl_part.setBold(true);
			sub_obl_part.setUnderline(UnderlinePatterns.SINGLE);

			XWPFParagraph parrafoI = documento.createParagraph();
			parrafoI.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aI = parrafoI.createRun();
			aI.setText(prfI1);
			aI.setBold(true);
			aI.setFontFamily("Arial");
			aI.setFontSize(12);
			XWPFRun bI = parrafoI.createRun();
			bI.setText(prfI2);
			bI.setFontFamily("Arial");
			bI.setFontSize(12);

			XWPFParagraph parrafoJ = documento.createParagraph();
			parrafoJ.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aJ = parrafoJ.createRun();
			aJ.setText(prfJ1);
			aJ.setBold(true);
			aJ.setFontFamily("Arial");
			aJ.setFontSize(12);
			XWPFRun bJ = parrafoJ.createRun();
			bJ.setText(prfJ2);
			bJ.setFontFamily("Arial");
			bJ.setFontSize(12);

			XWPFParagraph parrafoK = documento.createParagraph();
			parrafoK.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aK = parrafoK.createRun();
			aK.setText(prfK1);
			aK.setBold(true);
			aK.setFontFamily("Arial");
			aK.setFontSize(12);
			XWPFRun bK = parrafoK.createRun();
			bK.setText(prfK2);
			bK.setFontFamily("Arial");
			bK.setFontSize(12);
			XWPFRun cK = parrafoK.createRun();
			cK.setText(prfK3);
			cK.setBold(true);
			cK.setFontFamily("Arial");
			cK.setFontSize(12);
			XWPFRun dK = parrafoK.createRun();
			dK.setText(prfK4);
			dK.setFontFamily("Arial");
			dK.setFontSize(12);

			XWPFParagraph parrafoL = documento.createParagraph();
			parrafoL.setAlignment(ParagraphAlignment.BOTH);
			if (inquilino.getPropiedad().getCondicionPago().getResponsabilidadReparar()) {
				XWPFRun aL = parrafoL.createRun();
				aL.setText(prfL1_arrendador);
				aL.setBold(true);
				aL.setFontFamily("Arial");
				aL.setFontSize(12);
				XWPFRun bL = parrafoL.createRun();
				bL.setText(prfL2_arrendador);
				bL.setFontFamily("Arial");
				bL.setFontSize(12);
			} else {
				XWPFRun aL = parrafoL.createRun();
				aL.setText(prfL1_arrendatario);
				aL.setBold(true);
				aL.setFontFamily("Arial");
				aL.setFontSize(12);
				XWPFRun bL = parrafoL.createRun();
				bL.setText(prfL2_arrendatario);
				bL.setFontFamily("Arial");
				bL.setFontSize(12);
			}

			XWPFParagraph parrafoM = documento.createParagraph();
			parrafoM.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aM = parrafoM.createRun();
			aM.setText(prfM1);
			aM.setBold(true);
			aM.setFontFamily("Arial");
			aM.setFontSize(12);
			XWPFRun bM = parrafoM.createRun();
			bM.setText(prfM2);
			bM.setFontFamily("Arial");
			bM.setFontSize(12);
			XWPFRun cM = parrafoM.createRun();
			cM.setText(prfM3);
			cM.setBold(true);
			cM.setFontFamily("Arial");
			cM.setFontSize(12);
			XWPFRun dM = parrafoM.createRun();
			dM.setText(prfM4);
			dM.setFontFamily("Arial");
			dM.setFontSize(12);

			XWPFParagraph parrafoN = documento.createParagraph();
			parrafoN.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aN = parrafoN.createRun();
			aN.setText(prfN1);
			aN.setBold(true);
			aN.setFontFamily("Arial");
			aN.setFontSize(12);
			XWPFRun bN = parrafoN.createRun();
			bN.setText(prfN2);
			bN.setFontFamily("Arial");
			bN.setFontSize(12);
			bN.addBreak();

			XWPFParagraph subtitulo_clausu_penal = documento.createParagraph();
			subtitulo_clausu_penal.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun sub_clau_pen = subtitulo_clausu_penal.createRun();
			sub_clau_pen.setText(subtitle_claus_pena);
			sub_clau_pen.setFontFamily("Arial");
			sub_clau_pen.setFontSize(12);
			sub_clau_pen.setBold(true);
			sub_clau_pen.setUnderline(UnderlinePatterns.SINGLE);

			XWPFParagraph parrafoO = documento.createParagraph();
			parrafoO.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aO = parrafoO.createRun();
			aO.setText(prfO1);
			aO.setBold(true);
			aO.setFontFamily("Arial");
			aO.setFontSize(12);
			XWPFRun bO = parrafoO.createRun();
			bO.setText(prfO2);
			bO.setFontFamily("Arial");
			bO.setFontSize(12);
			XWPFRun cO = parrafoO.createRun();
			cO.setText(prfO3);
			cO.setBold(true);
			cO.setFontFamily("Arial");
			cO.setFontSize(12);
			XWPFRun dO = parrafoO.createRun();
			dO.setText(prfO4);
			dO.setFontFamily("Arial");
			dO.setFontSize(12);
			dO.addBreak();

			XWPFParagraph subtitulo_clausu_garantia = documento.createParagraph();
			subtitulo_clausu_garantia.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun sub_clau_gara = subtitulo_clausu_garantia.createRun();
			sub_clau_gara.setText(subtitle_clausu_garant);
			sub_clau_gara.setFontFamily("Arial");
			sub_clau_gara.setFontSize(12);
			sub_clau_gara.setBold(true);
			sub_clau_gara.setUnderline(UnderlinePatterns.SINGLE);

			XWPFParagraph parrafoP = documento.createParagraph();
			parrafoP.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aP = parrafoP.createRun();
			aP.setText(prfP1);
			aP.setBold(true);
			aP.setFontFamily("Arial");
			aP.setFontSize(12);
			XWPFRun bP = parrafoP.createRun();
			bP.setText(prfP2);
			bP.setFontFamily("Arial");
			bP.setFontSize(12);
			XWPFRun cP = parrafoP.createRun();
			cP.setText(prfP3);
			cP.setBold(true);
			cP.setFontFamily("Arial");
			cP.setFontSize(12);
			XWPFRun dP = parrafoP.createRun();
			dP.setText(prfP4);
			dP.setFontFamily("Arial");
			dP.setFontSize(12);
			dP.addBreak();

			XWPFParagraph subtitulo_clausu_conflicto = documento.createParagraph();
			subtitulo_clausu_conflicto.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun sub_clau_conf = subtitulo_clausu_conflicto.createRun();
			sub_clau_conf.setText(subtitle_clausu_sol_conflict);
			sub_clau_conf.setFontFamily("Arial");
			sub_clau_conf.setFontSize(12);
			sub_clau_conf.setBold(true);
			sub_clau_conf.setUnderline(UnderlinePatterns.SINGLE);

			XWPFParagraph parrafoQ = documento.createParagraph();
			parrafoQ.setAlignment(ParagraphAlignment.BOTH);
			XWPFRun aQ = parrafoQ.createRun();
			aQ.setText(prfQ1);
			aQ.setBold(true);
			aQ.setFontFamily("Arial");
			aQ.setFontSize(12);
			XWPFRun bQ = parrafoQ.createRun();
			bQ.setText(prfQ2);
			bQ.setFontFamily("Arial");
			bQ.setFontSize(12);
			XWPFRun cQ = parrafoQ.createRun();
			cQ.setText(prfQ3);
			cQ.setBold(true);
			cQ.setFontFamily("Arial");
			cQ.setFontSize(12);
			XWPFRun dQ = parrafoQ.createRun();
			dQ.setText(prfQ4);
			dQ.setFontFamily("Arial");
			dQ.setFontSize(12);
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
			aFirma.setText(firma);
			aFirma.setBold(true);
			aFirma.setFontFamily("Arial");
			aFirma.setFontSize(12);
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
			Optional<Contrato> op = repo_contrato.findById(id);
			Contrato contrato = op.isPresent() ? op.get() : new Contrato();
			String directorio = System.getProperty("user.dir");
			String separador = System.getProperty("file.separator");
			String ruta = directorio + separador + Constantes.rutaContrato + separador + contrato.getArchivoContrato()
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
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			return repo_contrato.findByArrendero(arrendero);
		} catch (Exception e) {
			throw e;
		}
	}

}
