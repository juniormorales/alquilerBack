package com.back.alquiler.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Pago;
import com.back.alquiler.models.Renta;
import com.back.alquiler.repo.PagoRepo;
import com.back.alquiler.repo.RentaRepo;
import com.back.alquiler.service.PagoService;
import com.back.alquiler.utils.Constantes;
import com.back.alquiler.utils.ConvertirNumeroALetras;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class PagoServiceImpl implements PagoService {

	@Autowired
	PagoRepo repoPago;

	@Autowired
	RentaRepo repoRenta;

	@javax.annotation.Resource(name = "dataSource")
	DataSource dataSource;

	private Double monto;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Pago registrar(Pago obj) {
		Renta renta = obj.getRenta();
		renta.setEnvioPago(0);
		repoRenta.save(renta);
		obj.setUrlVoucher("test");
		obj.setRechazado(false);
		obj.setFechaRegistro(new Date());
		obj.setInquilino(renta.getInquilino());
		obj.setBanco("");
		Pago respuesta = repoPago.save(obj);
		respuesta.setNro_boleta(String.valueOf(1000000 + respuesta.getIdPago()));
		return repoPago.save(respuesta);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Pago modificar(Pago obj) {
		monto = 0.0;
		ConvertirNumeroALetras letritas = new ConvertirNumeroALetras();
		obj.setFechaConfirmado(new Date());
		obj.setMontoLetras(letritas.convertir(obj.getMonto() + "", true));
		Renta renta = obj.getRenta();
		repoPago.findByRenta(renta).forEach(pago -> monto = monto + pago.getMonto());
		monto = monto + obj.getMonto();
		if (monto < renta.getCantidad()) {
			renta.setEnvioPago(3);
			obj.setMontoRestante(renta.getCantidad() - monto);
		} else {
			obj.setMontoRestante(0.0);
			renta.setEnvioPago(0);
			renta.setEstado(1);
		}
		renta.setMontoAcumuladoCancelado(monto);
		repoRenta.save(renta);
		return repoPago.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Pago> listar() {
		return repoPago.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoPago.existsById(id)) {
			repoPago.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Pago> listarPagosPorConfirmar(Integer id) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		return repoPago.findByArrenderoAndEstadoAndRechazado(arrendero, false, false);

	}

	@Override
	public Pago registrarImagenVoucher(MultipartFile archivo, Integer id) throws IOException {
		Optional<Pago> op = repoPago.findById(id);
		Pago pago = op.isPresent() ? op.get() : new Pago();
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = Paths.get(Constantes.RUTA_IMAGEN_VOUCHER).resolve(nombreArchivo).toAbsolutePath();
		Files.copy(archivo.getInputStream(), rutaArchivo);
		pago.setUrlVoucher(nombreArchivo);
		return repoPago.save(pago);

	}

	@Override
	public Resource verFotoVoucher(Integer id) throws IOException {
		Optional<Pago> op = repoPago.findById(id);
		Pago pago = op.isPresent() ? op.get() : new Pago();
		Path rutaArchivo = Paths.get(Constantes.RUTA_IMAGEN_VOUCHER).resolve(pago.getUrlVoucher()).toAbsolutePath();
		Resource recurso = null;
		recurso = new UrlResource(rutaArchivo.toUri());
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new IOException(Constantes.ERROR_CARGAR_FOTO + pago.getUrlVoucher());
		} else {
			return recurso;
		}

	}

	@Override
	public Pago rechazarPago(Pago pago) {
		Renta renta = pago.getRenta();
		renta.setEnvioPago(1);
		pago.setEstado(false);
		pago.setRechazado(true);
		pago.setMonto(0.0);
		repoRenta.save(renta);
		return repoPago.save(pago);

	}

	@Override
	public List<Pago> listarPagosTotalInquilino(Integer id) {
		Inquilino inquilino = new Inquilino();
		inquilino.setIdInquilino(id);
		return repoPago.findByInquilino(inquilino);

	}

	@Override
	public List<Pago> listarPagosArrendero(Integer id) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		return repoPago.findByArrenderoAndEstado(arrendero, true);

	}

	@Override
	public byte[] generarBoleta(Integer idPago) throws Exception {

		byte[] file = null;
		String directorio = System.getProperty("user.dir");
		String separador = System.getProperty("file.separator");
		String ruta = directorio + separador + Constantes.RUTA_BOLETA + separador;
		Map<String, Object> parametro = new HashMap<>();
		parametro.put("id_pago", idPago);
		parametro.put("rechazado", false);
		Connection cn = dataSource.getConnection();
		file = this.crearReporte(ruta, parametro, cn);
		return file;

	}

	private byte[] crearReporte(String ruta, Map<String, Object> parametor, Connection cn)
			throws FileNotFoundException, JRException {
		byte[] file = null;
		File fileroot = new File(ruta + "boleta_arrendamiento.jasper");
		FileInputStream input = new FileInputStream(fileroot);
		JasperPrint print = JasperFillManager.fillReport(input, parametor, cn);
		file = JasperExportManager.exportReportToPdf(print);
		return file;
	}

}
