package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.ImagenPropiedad;
import com.back.alquiler.repo.ImagenPropiedadRepo;
import com.back.alquiler.service.ImagenPropiedadService;

@Service
public class ImagenPropiedadServiceImpl implements ImagenPropiedadService {
	
	@Autowired
	ImagenPropiedadRepo repo_imagen_prop;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ImagenPropiedad registrar(ImagenPropiedad obj) {
		try {
			return repo_imagen_prop.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ImagenPropiedad modificar(ImagenPropiedad obj) {
		try {
			return repo_imagen_prop.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ImagenPropiedad leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ImagenPropiedad> listar() {
		return repo_imagen_prop.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
