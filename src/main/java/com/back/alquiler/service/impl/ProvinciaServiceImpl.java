package com.back.alquiler.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.alquiler.models.Departamento;
import com.back.alquiler.models.Provincia;
import com.back.alquiler.repo.ProvinciaRepo;
import com.back.alquiler.service.ProvinciaService;


@Service
public class ProvinciaServiceImpl implements ProvinciaService{

	@Autowired
	ProvinciaRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);
	
	@Override
	public Provincia registrar(Provincia obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provincia modificar(Provincia obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provincia leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Provincia> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Provincia> listarPorDepartamento(Departamento depa) {
		try {
			return repo.findByDepartamento(depa);
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" listarPorDepartamento. ERROR : "+e.getMessage());
			throw e;
		}
	}

}
