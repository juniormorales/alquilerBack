package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.CondicionPago;
import com.back.alquiler.repo.CondicionPagoRepo;
import com.back.alquiler.service.CondicionPagoService;

@Service
public class CondicionPagoServiceImpl implements CondicionPagoService {

	@Autowired
	CondicionPagoRepo repoCondicionPago;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CondicionPago registrar(CondicionPago obj) {
		return repoCondicionPago.save(obj);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CondicionPago modificar(CondicionPago obj) {
		return repoCondicionPago.save(obj);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CondicionPago> listar() {
		return repoCondicionPago.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoCondicionPago.existsById(id)) {
			repoCondicionPago.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<CondicionPago> listarPorArrendero(Integer id) {
		Arrendero a = new Arrendero();
		a.setIdArrendero(id);
		return repoCondicionPago.findByArrendero(a);
	}

}
