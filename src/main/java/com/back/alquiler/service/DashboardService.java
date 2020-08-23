package com.back.alquiler.service;

import java.util.List;
import java.util.Map;

import com.back.alquiler.dto.JsonGeneral;

public interface DashboardService {
	
	public List<Map<String, Object>> retornarGananciaAños(Integer id,List<JsonGeneral> anios);
	
	public List<Map<String,Object>>  retornaCantidadSolPropiedad(Integer id);
	
	public Long cantidadInquilinosAlDia(Integer id);
	
	public Long cantidadInquilinosDeudores(Integer id);
	
	public Long cantidadPagosPorConfirmar(Integer id);
	
	public Long cantidadSolicitudesPendientes(Integer id);
}	
