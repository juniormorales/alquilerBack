package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.UbicacionMaps;
import com.back.alquiler.repo.UbicacionMapsRepo;
import com.back.alquiler.service.UbicacionMapService;

@Service
public class UbicacionMapServiceImpl implements UbicacionMapService {
	
	@Autowired
	UbicacionMapsRepo repo_ubicacion;
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UbicacionMaps registrar(UbicacionMaps obj) {
		try {
			return repo_ubicacion.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UbicacionMaps modificar(UbicacionMaps obj) {
		try {
			return repo_ubicacion.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UbicacionMaps leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UbicacionMaps> listar() {
		return repo_ubicacion.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if(repo_ubicacion.existsById(id)) {
			repo_ubicacion.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
