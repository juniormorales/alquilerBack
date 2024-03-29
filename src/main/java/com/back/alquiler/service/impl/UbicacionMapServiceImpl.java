package com.back.alquiler.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.dto.JsonGeneral;
import com.back.alquiler.models.UbicacionMaps;
import com.back.alquiler.repo.UbicacionMapsRepo;
import com.back.alquiler.service.UbicacionMapService;
import com.back.alquiler.utils.Constantes;

@Service
public class UbicacionMapServiceImpl implements UbicacionMapService {

	@Autowired
	UbicacionMapsRepo repoUbicacion;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UbicacionMaps registrar(UbicacionMaps obj) {
		return repoUbicacion.save(obj);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UbicacionMaps modificar(UbicacionMaps obj) {
		return repoUbicacion.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<UbicacionMaps> listar() {
		return repoUbicacion.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoUbicacion.existsById(id)) {
			repoUbicacion.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<UbicacionMaps> listarPropiedadesDisponibles() {
		List<UbicacionMaps> lsMaps = repoUbicacion.findAll();
		return lsMaps.stream().filter(maps -> maps.getPropiedad().getConfirmado()).collect(Collectors.toList());

	}

	@Override
	public List<UbicacionMaps> filtrarPropiedadesDisponibleS(List<JsonGeneral> filtros) {

		List<UbicacionMaps> lsMaps = repoUbicacion.findAll().stream()
				.filter(maps -> maps.getPropiedad().getConfirmado()).collect(Collectors.toList());

		if (filtros.get(0) != null) {
			Integer idTarifa = filtros.get(0).getId();
			lsMaps = lsMaps.stream().filter(maps -> maps.getPropiedad().getCondicionPago()
					.getPrecio() >= Constantes.getDatatarifa()[idTarifa]
					&& maps.getPropiedad().getCondicionPago().getPrecio() <= Constantes.getDatatarifa()[idTarifa + 1])
					.collect(Collectors.toList());
		}

		if (filtros.get(1) != null) {
			Integer idPiso = filtros.get(1).getId();
			lsMaps = lsMaps.stream()
					.filter(maps -> maps.getPropiedad().getCantidadPisos() >= Constantes.getDatapisos()[idPiso]
							&& maps.getPropiedad().getCantidadPisos() <= Constantes.getDatapisos()[idPiso + 1])
					.collect(Collectors.toList());
		}

		if (filtros.get(2) != null) {
			Integer idHabitacion = filtros.get(2).getId();
			lsMaps = lsMaps.stream().filter(maps -> maps.getPropiedad()
					.getNroHabitaciones() >= Constantes.getDatahabitaciones()[idHabitacion]
					&& maps.getPropiedad().getNroHabitaciones() <= Constantes.getDatahabitaciones()[idHabitacion + 1])
					.collect(Collectors.toList());

		}
		return lsMaps;
	}

}
