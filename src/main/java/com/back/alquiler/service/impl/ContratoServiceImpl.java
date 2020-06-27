package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Contrato;
import com.back.alquiler.repo.ContratoRepo;
import com.back.alquiler.service.ContratoService;

@Service
public class ContratoServiceImpl implements ContratoService {
	
	@Autowired
	ContratoRepo repo_contrato;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Contrato registrar(Contrato obj) {
		try {
			return repo_contrato.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Contrato modificar(Contrato obj) {
		try {
			return repo_contrato.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Contrato leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contrato> listar() {
		return repo_contrato.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_contrato.existsById(id)) {
			repo_contrato.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
