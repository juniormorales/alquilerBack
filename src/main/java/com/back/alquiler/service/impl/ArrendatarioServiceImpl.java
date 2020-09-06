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
	ArrendatarioRepo repoArrendatario;

	@Autowired
	UsuarioRepo repoUsuario;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Arrendatario registrar(Arrendatario obj) {
		return repoArrendatario.save(obj);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Arrendatario modificar(Arrendatario obj) {
		return repoArrendatario.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Arrendatario> listar() {
		return repoArrendatario.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoArrendatario.existsById(id)) {
			repoArrendatario.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
