package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.SolicitudPropiedad;

public interface SolicitudPropiedadService extends ICRUD<SolicitudPropiedad>{
	
	List<SolicitudPropiedad> listarSolPendienteyAceptado(Integer id);
	List<SolicitudPropiedad> listarSolArrendatario(Integer id);
	List<SolicitudPropiedad> listarSolAceptadas(Integer id);
}
