package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServicioPublico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServicioPublico;

	@Column(nullable=false)
	private String descripcion;

	public Integer getIdServicioPublico() {
		return idServicioPublico;
	}

	public void setIdServicioPublico(Integer idServicioPublico) {
		this.idServicioPublico = idServicioPublico;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
