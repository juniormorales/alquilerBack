package com.back.alquiler.dto;

import com.back.alquiler.models.Usuario;

public class ArrendatarioNuevoDTO {
	
	private String direccionTemporal;

	private Usuario usuario;

	public String getDireccionTemporal() {
		return direccionTemporal;
	}

	public void setDireccionTemporal(String direccionTemporal) {
		this.direccionTemporal = direccionTemporal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
