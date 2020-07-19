package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Pago;
import com.back.alquiler.models.ReciboArrendamiento;
import com.back.alquiler.repo.PagoRepo;
import com.back.alquiler.repo.ReciboArrendamientoRepo;
import com.back.alquiler.service.ReciboArrendamientoService;

@Service
public class ReciboArrendamientoServiceImpl implements ReciboArrendamientoService {
	
	@Autowired
	ReciboArrendamientoRepo repo_recibo_arrend;
	
	@Autowired
	PagoRepo repo_pago;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ReciboArrendamiento registrar(ReciboArrendamiento obj) {
		try {
			return repo_recibo_arrend.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ReciboArrendamiento modificar(ReciboArrendamiento obj) {
		try {
			return repo_recibo_arrend.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ReciboArrendamiento leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReciboArrendamiento> listar() {
		return repo_recibo_arrend.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_recibo_arrend.existsById(id)) {
			repo_recibo_arrend.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean crearReciboArrendamiento(Pago pago) {
		try {
			pago.setReciboCreado(true);
			repo_pago.save(pago);
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

}
