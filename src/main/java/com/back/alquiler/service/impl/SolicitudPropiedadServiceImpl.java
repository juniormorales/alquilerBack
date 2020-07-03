package com.back.alquiler.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.SolicitudPropiedad;
import com.back.alquiler.repo.SolicitudPropiedadRepo;
import com.back.alquiler.service.SolicitudPropiedadService;

@Service
public class SolicitudPropiedadServiceImpl implements SolicitudPropiedadService {
	
	@Autowired
	SolicitudPropiedadRepo repo_sol_prop;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SolicitudPropiedad registrar(SolicitudPropiedad obj) {
		try {
			return repo_sol_prop.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SolicitudPropiedad modificar(SolicitudPropiedad obj) {
		try {
			return repo_sol_prop.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public SolicitudPropiedad leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SolicitudPropiedad> listar() {
		return repo_sol_prop.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_sol_prop.existsById(id)) {
			repo_sol_prop.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<SolicitudPropiedad> listarSolPendienteyAceptado(Integer id) {
		try {
			Arrendero a = new Arrendero();
			a.setIdArrendero(id);
			List<SolicitudPropiedad> lsSol = repo_sol_prop.findByArrendero(a);
			lsSol = lsSol.stream().filter( sol -> sol.getEstado()==1 || sol.getEstado()==2).collect(Collectors.toList());
			return lsSol;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SolicitudPropiedad> listarSolArrendatario(Integer id) {
		try {
			Arrendatario a = new Arrendatario();
			a.setIdArrendatario(id);
			return repo_sol_prop.findByArrendatario(a);
		} catch (Exception e) {
			throw e;
		}
	}

}
