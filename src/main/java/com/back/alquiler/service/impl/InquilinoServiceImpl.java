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
	InquilinoRepo repoInquilino;

	@Autowired
	ContratoRepo repoContrato;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Inquilino registrar(Inquilino obj) {

		return repoInquilino.save(obj);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Inquilino modificar(Inquilino obj) {
		return repoInquilino.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Inquilino> listar() {
		return repoInquilino.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoInquilino.existsById(id)) {
			repoInquilino.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Inquilino> listarPorArrenderoYContratoHecho(Integer id) {
		Arrendero a = new Arrendero();
		a.setIdArrendero(id);
		return repoInquilino.findByArrenderoAndContratoHechoAndEstado(a, true, true);

	}

	@Override
	public Boolean darBajaInquilino(Inquilino inquilino) {
		Contrato cont = repoContrato.findByInquilinoAndCaduco(inquilino, false);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(cont.getFechaInicio());
		calendar.add(Calendar.MONTH, cont.getTiempoContrato());
		Date fechaActual = new Date();
		if (calendar.getTime().compareTo(fechaActual) > 0) {
			return false;
		} else {
			cont.setCaduco(true);
			inquilino.setEstado(false);
			repoInquilino.save(inquilino);
			repoContrato.save(cont);
			return true;
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
		return repoInquilino.save(in);

	}

	@Override
	public List<Inquilino> listarPorArrenderoYContratoNoHecho(Integer id) {
		Arrendero a = new Arrendero();
		a.setIdArrendero(id);
		return repoInquilino.findByArrenderoAndContratoHechoAndEstado(a, false, true);

	}

	@Override
	public Inquilino obtenerInquilinoActivo(Integer id) {
		Arrendatario arrendatario = new Arrendatario();
		arrendatario.setIdArrendatario(id);
		return repoInquilino.findByArrendatarioAndEstado(arrendatario, true);

	}

}
