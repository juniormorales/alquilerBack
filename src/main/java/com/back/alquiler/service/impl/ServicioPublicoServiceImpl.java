package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.ServicioPublico;
import com.back.alquiler.repo.ServicioPublicoRepo;
import com.back.alquiler.service.ServicioPublicoService;

@Service
public class ServicioPublicoServiceImpl implements ServicioPublicoService {
	
	@Autowired
	ServicioPublicoRepo repo;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ServicioPublico registrar(ServicioPublico obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ServicioPublico modificar(ServicioPublico obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ServicioPublico leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ServicioPublico> listar() {
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
