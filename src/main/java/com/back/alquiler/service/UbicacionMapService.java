package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.dto.JsonGeneral;
import com.back.alquiler.models.UbicacionMaps;

public interface UbicacionMapService extends ICRUD<UbicacionMaps>{
	
	List<UbicacionMaps> listarPropiedadesDisponibles();
	
	List<UbicacionMaps> filtrarPropiedadesDisponibleS(List<JsonGeneral> filtros);
}
