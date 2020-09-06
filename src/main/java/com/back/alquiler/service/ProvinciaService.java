package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Departamento;
import com.back.alquiler.models.Provincia;


public interface ProvinciaService{
	
	public List<Provincia> listarPorDepartamento(Departamento depa);

}
