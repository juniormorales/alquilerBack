package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.alquiler.models.Banco;
import com.back.alquiler.repo.BancoRepo;
import com.back.alquiler.service.BancoService;

@Service
public class BancoServiceImpl implements BancoService {
	
	@Autowired
	BancoRepo repo;
	
	@Override
	public Banco registrar(Banco obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Banco modificar(Banco obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Banco leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Banco> listar() {
		return repo.findAll();
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
