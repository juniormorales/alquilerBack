package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.repo.ArrendatarioRepo;
import com.back.alquiler.repo.UsuarioRepo;
import com.back.alquiler.service.ArrendatarioService;

@Service
public class ArrendatarioServiceImpl implements ArrendatarioService {
	
	@Autowired
	ArrendatarioRepo repo_arrendatario;
	
	@Autowired
	UsuarioRepo repo_usuario;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Arrendatario registrar(Arrendatario obj) {
		try {
			return repo_arrendatario.save(obj);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Arrendatario modificar(Arrendatario obj) {
		try {
			return repo_arrendatario.save(obj);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly =true)
	public Arrendatario leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly =true)
	public List<Arrendatario> listar() {
		return repo_arrendatario.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_arrendatario.existsById(id)) {
			repo_arrendatario.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
