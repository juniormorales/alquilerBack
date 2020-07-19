package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Renta;
import com.back.alquiler.repo.RentaRepo;
import com.back.alquiler.service.RentaService;

@Service
public class RentaServiceImpl implements RentaService {
	
	@Autowired
	RentaRepo repo_renta;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Renta registrar(Renta obj) {
		try {
			return repo_renta.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Renta modificar(Renta obj) {
		try {
			return repo_renta.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Renta leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Renta> listar() {
		return repo_renta.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_renta.existsById(id)) {
			repo_renta.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Renta> listarRentasPendientes(Inquilino inquilino) {
		try {
			return repo_renta.findByInquilinoAndEstado(inquilino, 0);
		} catch (Exception e) {
			throw e;
		}
	}

}
