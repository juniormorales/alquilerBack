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
	RentaRepo repoRenta;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Renta registrar(Renta obj) {
		return repoRenta.save(obj);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Renta modificar(Renta obj) {
		return repoRenta.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Renta> listar() {
		return repoRenta.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoRenta.existsById(id)) {
			repoRenta.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Renta> listarRentasPendientes(Inquilino inquilino) {
		return repoRenta.findByInquilinoAndEstadoOrderByFechaFinRentaAsc(inquilino, 0);

	}

	@Override
	public List<Renta> listarRentasCanceladas(Inquilino inquilino) {
		return repoRenta.findByInquilinoAndEstadoOrderByFechaFinRentaAsc(inquilino, 1);

	}

	@Override
	public List<Renta> listarDeudasRenta(Inquilino inquilino) {
		return repoRenta.findByInquilinoAndEstadoOrderByFechaFinRentaAsc(inquilino, 2);

	}

}
