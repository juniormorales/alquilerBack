package com.back.alquiler.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Propiedad;
import com.back.alquiler.models.SolicitudPropiedad;
import com.back.alquiler.repo.SolicitudPropiedadRepo;
import com.back.alquiler.service.SolicitudPropiedadService;

@Service
public class SolicitudPropiedadServiceImpl implements SolicitudPropiedadService {

	@Autowired
	SolicitudPropiedadRepo repoSolProp;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SolicitudPropiedad registrar(SolicitudPropiedad obj) {
		obj.setFechaSolicitud(new Date());
		return repoSolProp.save(obj);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SolicitudPropiedad modificar(SolicitudPropiedad obj) {
		return repoSolProp.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<SolicitudPropiedad> listar() {
		return repoSolProp.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoSolProp.existsById(id)) {
			repoSolProp.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<SolicitudPropiedad> listarSolPendienteyAceptado(Integer id) {
		Arrendero a = new Arrendero();
		a.setIdArrendero(id);
		List<SolicitudPropiedad> lsSol = repoSolProp.findByArrendero(a);
		lsSol = lsSol.stream().filter(sol -> sol.getEstado() == 1 || sol.getEstado() == 2).collect(Collectors.toList());
		return lsSol;

	}

	@Override
	public List<SolicitudPropiedad> listarSolArrendatario(Integer id) {
		Arrendatario a = new Arrendatario();
		a.setIdArrendatario(id);
		List<SolicitudPropiedad> lsSol = repoSolProp.findByArrendatario(a);
		lsSol = lsSol.stream().filter(sol -> sol.getEstado() != 1).collect(Collectors.toList());
		return lsSol;

	}

	@Override
	public List<SolicitudPropiedad> listarSolAceptadas(Integer id) {
		Arrendatario a = new Arrendatario();
		a.setIdArrendatario(id);
		List<SolicitudPropiedad> lsSol = repoSolProp.findByArrendatario(a);
		lsSol = lsSol.stream().filter(sol -> sol.getEstado() == 1).collect(Collectors.toList());
		return lsSol;

	}

	@Override
	public Boolean buscarSolExistente(Integer idPropiedad, Integer idArrendatario) {
		Propiedad prop = new Propiedad();
		Arrendatario arrendatario = new Arrendatario();
		prop.setIdPropiedad(idPropiedad);
		arrendatario.setIdArrendatario(idArrendatario);
		SolicitudPropiedad sol = repoSolProp.findByPropiedadAndArrendatario(prop, arrendatario);
		if (sol != null) {
			return sol.getEstado() != 4;
		} else {
			return false;
		}

	}
}
