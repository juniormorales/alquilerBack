package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Distrito;
import com.back.alquiler.models.Provincia;
import com.back.alquiler.repo.DistritoRepo;
import com.back.alquiler.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService {

	@Autowired
	DistritoRepo repo;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Distrito> listarPorProvincia(Provincia prov) {
		return repo.findByProvincia(prov);
	}
}
