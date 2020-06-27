package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Distrito;
import com.back.alquiler.models.Provincia;



public interface DistritoService extends ICRUD<Distrito>{
	
	public List<Distrito> listarPorProvincia(Provincia prov); 

}
