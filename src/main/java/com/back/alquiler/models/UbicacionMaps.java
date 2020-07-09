package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UbicacionMaps {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUbicacionMaps;
	
	@Column(nullable=false)
	private Double latitud;
	
	@Column(nullable=false)
	private Double longitud;
	
	@Column(nullable=false)
	private String descripcionDireccion;
	
	@ManyToOne
	@JoinColumn(name="id_propiedad")
	private Propiedad propiedad;

	public Integer getIdUbicacionMaps() {
		return idUbicacionMaps;
	}

	public void setIdUbicacionMaps(Integer idUbicacionMaps) {
		this.idUbicacionMaps = idUbicacionMaps;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	public String getDescripcionDireccion() {
		return descripcionDireccion;
	}

	public void setDescripcionDireccion(String descripcionDireccion) {
		this.descripcionDireccion = descripcionDireccion;
	}
	
}
