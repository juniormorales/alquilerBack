package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Calificacion;

public interface CalificacionService extends ICRUD<Calificacion> {
	
	List<Calificacion> listarCalificacionesArrendatario(Integer id);
}
