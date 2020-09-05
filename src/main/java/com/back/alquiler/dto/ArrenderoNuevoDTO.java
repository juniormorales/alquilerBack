package com.back.alquiler.dto;


import com.back.alquiler.models.Departamento;
import com.back.alquiler.models.Distrito;
import com.back.alquiler.models.Provincia;
import com.back.alquiler.models.Usuario;

public class ArrenderoNuevoDTO {
	
	public String direccionActual;
	
	public Integer nroPartidaRegistral;
	
	public Usuario usuario;
	
	public Departamento departamento;
	
	public Provincia provincia;

	public Distrito distrito;

}
