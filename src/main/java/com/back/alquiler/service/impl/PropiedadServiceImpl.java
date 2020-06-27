package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Propiedad;
import com.back.alquiler.repo.PropiedadRepo;
import com.back.alquiler.service.PropiedadService;

@Service
public class PropiedadServiceImpl implements PropiedadService {
	
	@Autowired
	PropiedadRepo repo_propiedad;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Propiedad registrar(Propiedad obj) {
		try {
			return repo_propiedad.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Propiedad modificar(Propiedad obj) {
		try {
			return repo_propiedad.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Propiedad leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Propiedad> listar() {
		return repo_propiedad.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_propiedad.existsById(id)) {
			repo_propiedad.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
