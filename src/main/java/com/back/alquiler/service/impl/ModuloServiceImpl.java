package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.alquiler.models.Modulo;
import com.back.alquiler.models.Perfil;
import com.back.alquiler.repo.ModuloRepo;
import com.back.alquiler.service.ModuloService;

@Service
public class ModuloServiceImpl implements ModuloService {

	@Autowired
	private ModuloRepo repo;

	@Override
	public List<Modulo> listarModuloPorPerfil(Perfil perfil) {
		return repo.listarModuloPorPerfil(perfil.getIdPerfil());
	}
}
