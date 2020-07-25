package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Banco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idBanco;
	
	@Column(nullable=false)
	private String descripcion;

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Banco(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Banco() {
		
	}
	

}
