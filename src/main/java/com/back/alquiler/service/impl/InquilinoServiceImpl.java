package com.back.alquiler.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Contrato;
import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.SolicitudPropiedad;
import com.back.alquiler.repo.ContratoRepo;
import com.back.alquiler.repo.InquilinoRepo;
import com.back.alquiler.service.InquilinoService;

@Service
public class InquilinoServiceImpl implements InquilinoService {
	
	@Autowired
	InquilinoRepo repo_inquilino;
	
	@Autowired
	ContratoRepo repo_contrato;
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Inquilino registrar(Inquilino obj) {
		try {
			return repo_inquilino.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Inquilino modificar(Inquilino obj) {
		try {
			return repo_inquilino.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Inquilino leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Inquilino> listar() {
		return repo_inquilino.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_inquilino.existsById(id)) {
			repo_inquilino.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Inquilino> listarPorArrenderoYContratoHecho(Integer id) {
		try {
			Arrendero a = new Arrendero();
			a.setIdArrendero(id);
			return repo_inquilino.findByArrenderoAndContratoHechoAndEstado(a, true, true);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean darBajaInquilino(Inquilino inquilino) {
		try {
			Contrato cont = repo_contrato.findByInquilinoAndCaduco(inquilino, false);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(cont.getFechaInicio()); 
			calendar.add(Calendar.MONTH,cont.getTiempoContrato());
			Date fecha_actual = new Date();
			if(calendar.getTime().compareTo(fecha_actual)>0) {
				return false;
			}else {
				inquilino.setEstado(false);
				repo_inquilino.save(inquilino);
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Inquilino registrarInquilinoSinContrato(SolicitudPropiedad sol) {
		Inquilino in = new Inquilino();
		in.setArrendatario(sol.getArrendatario());
		in.setArrendero(sol.getArrendero());
		in.setContratoHecho(false);
		in.setEstado(true);
		in.setEstadoPago(true);
		in.setFechaConfirmacion(new Date());
		in.setPropiedad(sol.getPropiedad());
		try {
			return repo_inquilino.save(in);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Inquilino> listarPorArrenderoYContratoNoHecho(Integer id) {
		try {
			Arrendero a = new Arrendero();
			a.setIdArrendero(id);
			return repo_inquilino.findByArrenderoAndContratoHechoAndEstado(a, false, true);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Inquilino obtenerInquilinoActivo(Integer id) {
		try {
			Arrendatario arrendatario = new Arrendatario();
			arrendatario.setIdArrendatario(id);
			return repo_inquilino.findByArrendatarioAndEstado(arrendatario,true);
		} catch (Exception e) {
			throw e;
		}
	}	

}
