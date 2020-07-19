package com.back.alquiler.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

@Service
public class PagoServiceImpl implements PagoService {

	@Autowired
	PagoRepo repo_pago;

	@Autowired
	RentaRepo repo_renta;
	
	private Double monto;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Pago registrar(Pago obj) {
		try {
			Renta renta = obj.getRenta();
			renta.setEnvioPago(0);
			repo_renta.save(renta);
			obj.setUrlVoucher("test");
			obj.setRechazado(false);
			obj.setFechaRegistro(new Date());
			obj.setInquilino(renta.getInquilino());
			return repo_pago.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Pago modificar(Pago obj) {
		monto =0.0;
		try {
			Renta renta = obj.getRenta();
			repo_pago.findByRenta(renta).forEach( pago ->{
				monto = monto + pago.getMonto();
			});
			monto = monto + obj.getMonto();
			
			if(monto < renta.getCantidad()) {
				renta.setEnvioPago(3);
				obj.setMontoRestante(renta.getCantidad() - monto);
			}else {
				obj.setMontoRestante(0.0);
				renta.setEnvioPago(0);
				renta.setEstado(1);
			}
			renta.setMontoAcumuladoCancelado(monto);
			repo_renta.save(renta);
			return repo_pago.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Pago leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pago> listar() {
		return repo_pago.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repo_pago.existsById(id)) {
			repo_pago.deleteById(id);
			return true;
		} else
			return false;
	}

	@Override
	public List<Pago> listarPagosPorConfirmar(Integer id) {
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			return repo_pago.findByArrenderoAndEstadoAndRechazado(arrendero, false,false);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Pago registrarImagenVoucher(MultipartFile archivo, Integer id) throws IOException {
		Pago pago = repo_pago.findById(id).get();
		try {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get(Constantes.rutaImagenVoucher).resolve(nombreArchivo).toAbsolutePath();
			Files.copy(archivo.getInputStream(), rutaArchivo);
			pago.setUrlVoucher(nombreArchivo);
			return repo_pago.save(pago);

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Resource verFotoVoucher(Integer id) throws IOException {
		Pago pago = repo_pago.findById(id).get();
		Path rutaArchivo = Paths.get(Constantes.rutaImagenVoucher).resolve(pago.getUrlVoucher()).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
			if (!recurso.exists() && !recurso.isReadable()) {
				throw new RuntimeException(Constantes.errorCargarFoto + pago.getUrlVoucher());
			}else {
				return recurso;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Pago rechazarPago(Pago pago) {
		try {
			Renta renta = pago.getRenta();
			renta.setEnvioPago(1);
			pago.setEstado(false);
			pago.setRechazado(true);
			pago.setMonto(0.0);
			repo_renta.save(renta);
			return repo_pago.save(pago);
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Pago> listarPagosTotalInquilino(Integer id) {
		try {
			Inquilino inquilino = new Inquilino();
			inquilino.setIdInquilino(id);
			return repo_pago.findByInquilino(inquilino);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Pago> listarPagosArrendero(Integer id) {
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			return repo_pago.findByArrenderoAndEstado(arrendero, true);
		} catch (Exception e) {
			throw e;
		}
	}

}
