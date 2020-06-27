package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

}
