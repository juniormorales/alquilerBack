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
	BancoRepo repoBanco;

	@Override
	public Banco registrar(Banco obj) {
		return repoBanco.save(obj);

	}

	@Override
	public Banco modificar(Banco obj) {
		return repoBanco.save(obj);
	}

	@Override
	public List<Banco> listar() {
		return repoBanco.findAll();
	}

	@Override
	public Boolean eliminar(Integer id) {
		if (repoBanco.existsById(id)) {
			repoBanco.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
