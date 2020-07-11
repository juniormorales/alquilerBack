package com.back.alquiler.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Propiedad;
import com.back.alquiler.models.UbicacionMaps;
import com.back.alquiler.repo.PropiedadRepo;
import com.back.alquiler.repo.UbicacionMapsRepo;
import com.back.alquiler.service.PropiedadService;
import com.back.alquiler.service.UbicacionMapService;

@Service
public class PropiedadServiceImpl implements PropiedadService {

	@Autowired
	PropiedadRepo repo_propiedad;
	
	@Autowired
	UbicacionMapsRepo repo_ubicacion;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Propiedad registrar(Propiedad obj) {
		try {
			if (!obj.getTieneDanios()) {
				obj.setDescripcionDanios("La propiedad no presenta ningun daño en las instalaciones");
			}
			switch (obj.getEstado()) {
			case 0:
				obj.setCondicionPropiedad("Fuera de servicio");
				break;
			case 1:
				obj.setCondicionPropiedad("Disponible");
				break;
			case 2:
				obj.setCondicionPropiedad("En mantenimiento");
				break;
			case 3:
				obj.setCondicionPropiedad("Ya ocupado");
				break;
			}
			obj.setFechaRegistro(new Date());
			return repo_propiedad.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Propiedad modificar(Propiedad obj) {
		try {
			if (!obj.getTieneDanios()) {
				obj.setDescripcionDanios("La propiedad no presenta ningun daño en las instalaciones");
			}
			switch (obj.getEstado()) {
			case 0:
				obj.setCondicionPropiedad("Fuera de servicio");
				break;
			case 1:
				obj.setCondicionPropiedad("Disponible");
				break;
			case 2:
				obj.setCondicionPropiedad("En mantenimiento");
				break;
			case 3:
				obj.setCondicionPropiedad("Ya ocupado");
				break;
			}
			return repo_propiedad.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Propiedad leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Propiedad> listar() {
		return repo_propiedad.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		Propiedad p = new Propiedad();
		p.setIdPropiedad(id);
		repo_ubicacion.deleteByPropiedad(p);
		
		if (repo_propiedad.existsById(id)) {
			repo_propiedad.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Propiedad> listarPorArrendero(Integer id) {
		Arrendero a = new Arrendero();
		a.setIdArrendero(id);
		try {
			return repo_propiedad.findByArrenderoAndConfirmado(a,true);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<UbicacionMaps> listarNoAceptadas() {
		try {
			List<Propiedad> lsProp = repo_propiedad.findByConfirmadoAndRechazado(false,false);
			List<UbicacionMaps> maps = new ArrayList<>();
			lsProp.forEach( propiedad -> {
				maps.add(repo_ubicacion.findByPropiedad(propiedad));
			});
			
			return maps;
		} catch (Exception e) {
			throw e;
		}
	}

}
