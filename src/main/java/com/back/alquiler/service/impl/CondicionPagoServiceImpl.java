package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.CondicionPago;
import com.back.alquiler.repo.CondicionPagoRepo;
import com.back.alquiler.service.CondicionPagoService;

@Service
public class CondicionPagoServiceImpl implements CondicionPagoService {
	
	@Autowired
	CondicionPagoRepo repo_condicion_pago;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CondicionPago registrar(CondicionPago obj) {
		try {
			return repo_condicion_pago.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CondicionPago modificar(CondicionPago obj) {
		try {
			return repo_condicion_pago.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public CondicionPago leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CondicionPago> listar() {
		return repo_condicion_pago.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_condicion_pago.existsById(id)) {
			repo_condicion_pago.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
