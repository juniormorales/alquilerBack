package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Propiedad;

public interface PropiedadService extends ICRUD<Propiedad>{
	
	List<Propiedad> listarPorArrendero(Integer id);
}
