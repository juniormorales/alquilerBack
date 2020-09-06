package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.alquiler.models.Departamento;
import com.back.alquiler.models.Provincia;
import com.back.alquiler.repo.ProvinciaRepo;
import com.back.alquiler.service.ProvinciaService;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

	@Autowired
	ProvinciaRepo repo;

	@Override
	public List<Provincia> listarPorDepartamento(Departamento depa) {
		return repo.findByDepartamento(depa);

	}

}
