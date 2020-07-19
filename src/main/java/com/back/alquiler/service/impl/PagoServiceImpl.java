package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Pago;
import com.back.alquiler.repo.PagoRepo;
import com.back.alquiler.repo.RentaRepo;
import com.back.alquiler.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {
	
	@Autowired
	PagoRepo repo_pago;
	
	@Autowired
	RentaRepo repo_renta;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Pago registrar(Pago obj) {
		try {
			obj.setUrlVoucher("test");
			return repo_pago.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Pago modificar(Pago obj) {
		try {
			return repo_pago.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Pago leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pago> listar() {
		return repo_pago.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_pago.existsById(id)) {
			repo_pago.deleteById(id);
			return true;
		}else
			return false;
	}

	@Override
	public Pago confirmarPago(Pago pago) {
		try {
			return repo_pago.save(pago);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Pago> listarPagosPorConfirmar(Integer id) {
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			return repo_pago.findByArrenderoAndEstado(arrendero, false);
		} catch (Exception e) {
			throw e;
		}
	}

}
