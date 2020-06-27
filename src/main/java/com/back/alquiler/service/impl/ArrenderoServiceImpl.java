package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.repo.ArrenderoRepo;
import com.back.alquiler.service.ArrenderoService;

@Service
public class ArrenderoServiceImpl implements ArrenderoService {
	
	@Autowired
	ArrenderoRepo repo_arrendero;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Arrendero registrar(Arrendero obj) {
		try {
			return repo_arrendero.save(obj);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Arrendero modificar(Arrendero obj) {
		try {
			return repo_arrendero.save(obj);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Arrendero leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Arrendero> listar() {
		return repo_arrendero.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_arrendero.existsById(id)) {
			repo_arrendero.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
