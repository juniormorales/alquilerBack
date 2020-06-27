package com.back.alquiler.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.alquiler.models.Departamento;
import com.back.alquiler.repo.DepartamentoRepo;
import com.back.alquiler.service.DepartamentoService;


@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	DepartamentoRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);
	
	@Override
	public Departamento registrar(Departamento obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Departamento modificar(Departamento obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Departamento leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> listar() {
		try {
			return repo.findAll();
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+"listarDepartamento. ERROR : "+e.getMessage());
			throw e;
		}
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
