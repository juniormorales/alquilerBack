package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.DetallePropiedadServicioPublico;
import com.back.alquiler.repo.DetallePropiedadServicioPublicoRepo;
import com.back.alquiler.service.DetallePropiedadServicioPublicoService;

@Service
public class DetallePropiedadServicioPublicoServiceImpl implements DetallePropiedadServicioPublicoService {
	
	@Autowired
	DetallePropiedadServicioPublicoRepo repo;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DetallePropiedadServicioPublico registrar(DetallePropiedadServicioPublico obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DetallePropiedadServicioPublico modificar(DetallePropiedadServicioPublico obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public DetallePropiedadServicioPublico leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetallePropiedadServicioPublico> listar() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
