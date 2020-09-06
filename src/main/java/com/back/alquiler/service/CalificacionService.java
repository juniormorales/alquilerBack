package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.dto.CalificacionDTO;
import com.back.alquiler.models.Calificacion;

public interface CalificacionService {
	
	List<Calificacion> listarCalificacionesArrendatario(Integer id);
	
	Calificacion registrarCalificacion(CalificacionDTO dto);
}
