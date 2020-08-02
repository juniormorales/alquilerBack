package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Calificacion;
import com.back.alquiler.repo.CalificacionRepo;
import com.back.alquiler.service.CalificacionService;

@Service
public class CalificacionServiceImpl implements CalificacionService {
	
	@Autowired
	CalificacionRepo repo_calificacion;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Calificacion registrar(Calificacion obj) {
		try {
			return repo_calificacion.save(obj);
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Calificacion modificar(Calificacion obj) {
		try {
			return repo_calificacion.save(obj);
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Calificacion leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Calificacion> listar() {
		return repo_calificacion.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_calificacion.existsById(id)) {
			repo_calificacion.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Calificacion> listarCalificacionesArrendatario(Integer id) {
		try {
			Arrendatario arrendatario = new Arrendatario();
			arrendatario.setIdArrendatario(id);
			return repo_calificacion.findByArrendatario(arrendatario);
		} catch (Exception e) {
			throw e;
		}
	}

}
