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

@Service
public class PropiedadServiceImpl implements PropiedadService {

	@Autowired
	PropiedadRepo repoPropiedad;

	@Autowired
	UbicacionMapsRepo repoUbicacion;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Propiedad registrar(Propiedad obj) {
		setData(obj);
		obj.setFechaRegistro(new Date());
		return repoPropiedad.save(obj);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Propiedad modificar(Propiedad obj) {
		setData(obj);
		return repoPropiedad.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Propiedad> listar() {
		return repoPropiedad.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		Propiedad p = new Propiedad();
		p.setIdPropiedad(id);
		repoUbicacion.deleteByPropiedad(p);

		if (repoPropiedad.existsById(id)) {
			repoPropiedad.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Propiedad> listarPorArrendero(Integer id) {
		Arrendero a = new Arrendero();
		a.setIdArrendero(id);
		return repoPropiedad.findByArrenderoAndConfirmado(a, true);

	}

	@Override
	public List<UbicacionMaps> listarNoAceptadas() {
		List<Propiedad> lsProp = repoPropiedad.findByConfirmadoAndRechazado(false, false);
		List<UbicacionMaps> maps = new ArrayList<>();
		lsProp.forEach(propiedad ->  maps.add(repoUbicacion.findByPropiedad(propiedad)));
		return maps;
	}
	
	private void  setData(Propiedad obj) {
		if (Boolean.FALSE.equals(obj.getTieneDanios())) {
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
		default:
			obj.setCondicionPropiedad("No definido");
			break;
		}
	}

}
