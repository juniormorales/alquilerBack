package com.back.alquiler.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Distrito;
import com.back.alquiler.models.Provincia;
import com.back.alquiler.repo.DistritoRepo;
import com.back.alquiler.service.DistritoService;



@Service
public class DistritoServiceImpl implements DistritoService{
	
	@Autowired
	DistritoRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Distrito> listarPorProvincia(Provincia prov) {
		try {
			return repo.findByProvincia(prov);
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" listarPorProvincia. ERROR : "+e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Distrito registrar(Distrito obj) {
		try {
			return repo.save(obj);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Distrito modificar(Distrito obj) {
		return repo.save(obj);
	}

	@Override
	public Distrito leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Distrito> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
