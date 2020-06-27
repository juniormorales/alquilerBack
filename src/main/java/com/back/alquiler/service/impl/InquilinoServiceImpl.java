package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.repo.InquilinoRepo;
import com.back.alquiler.service.InquilinoService;

@Service
public class InquilinoServiceImpl implements InquilinoService {
	
	@Autowired
	InquilinoRepo repo_inquilino;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Inquilino registrar(Inquilino obj) {
		try {
			return repo_inquilino.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Inquilino modificar(Inquilino obj) {
		try {
			return repo_inquilino.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Inquilino leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Inquilino> listar() {
		return repo_inquilino.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_inquilino.existsById(id)) {
			repo_inquilino.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
