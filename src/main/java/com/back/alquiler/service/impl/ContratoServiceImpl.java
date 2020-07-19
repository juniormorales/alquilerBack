package com.back.alquiler.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Contrato;
import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Renta;
import com.back.alquiler.repo.ContratoRepo;
import com.back.alquiler.repo.InquilinoRepo;
import com.back.alquiler.repo.RentaRepo;
import com.back.alquiler.repo.SolicitudPropiedadRepo;
import com.back.alquiler.service.ContratoService;

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
		if(repo_contrato.existsById(id)) {
			repo_contrato.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Contrato habilitarContrato(Inquilino inquilino) {
		inquilino.setContratoHecho(true);
		Contrato contrato = new Contrato();
		try {
			Inquilino resp = repo_inquilino.save(inquilino);
			contrato.setCaduco(false);
			contrato.setFechaEmision( new Date());
			contrato.setInquilino(resp);
			contrato.setTiempoContrato(
					repo_solicitud_pro.findByArrendatarioAndPropiedadOrderByFechaSolicitudDesc(resp.getArrendatario(), 
							resp.getPropiedad()).getTiempoArrendamiento()
					);
			contrato.setFechaInicio(inquilino.getFecha_inicio());
			for(int i=1; i<=contrato.getTiempoContrato();i++) {
				LocalDate fecha_inicio = Instant.ofEpochMilli(contrato.getFechaInicio().getTime()).atZone(ZoneId.systemDefault())
					      .toLocalDate();
				LocalDate fecha_mes_prox = fecha_inicio.plusMonths(i);
				Renta renta = new Renta();
				renta.setCantidad(resp.getPropiedad().getCondicionPago().getPrecio());
				renta.setEstado(0);
				renta.setFechaRenta(java.sql.Date.valueOf(fecha_mes_prox));
				renta.setImporteAtrasado(inquilino.getPropiedad().getCondicionPago().getTasaRecargo()*renta.getCantidad()*0.01);
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

}
