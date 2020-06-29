package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ImagenPropiedad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idImagenPropiedad;
	
	@Column(nullable=false)
	private String nombreFoto;
	
	@ManyToOne
	@JoinColumn(name="id_propiedad", nullable=false)
	private Propiedad propiedad;

	public Integer getIdImagenPropiedad() {
		return idImagenPropiedad;
	}

	public void setIdImagenPropiedad(Integer idImagenPropiedad) {
		this.idImagenPropiedad = idImagenPropiedad;
	}

	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String ruta) {
		this.nombreFoto = ruta;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}
	
	
}
