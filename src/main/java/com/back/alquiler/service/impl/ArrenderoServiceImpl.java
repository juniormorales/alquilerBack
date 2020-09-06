package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.repo.ArrenderoRepo;
import com.back.alquiler.service.ArrenderoService;

@Service
public class ArrenderoServiceImpl implements ArrenderoService {

	@Autowired
	ArrenderoRepo repoArrendero;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Arrendero registrar(Arrendero obj) {
		return repoArrendero.save(obj);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Arrendero modificar(Arrendero obj) {
		return repoArrendero.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Arrendero> listar() {
		return repoArrendero.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoArrendero.existsById(id)) {
			repoArrendero.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
