package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.FiltroDTO;
import com.back.alquiler.models.UbicacionMaps;

public interface UbicacionMapService extends ICRUD<UbicacionMaps>{
	
	List<UbicacionMaps> listarPropiedadesDisponibles();
	
	List<UbicacionMaps> filtrarPropiedadesDisponibleS(List<FiltroDTO> filtros);
}
