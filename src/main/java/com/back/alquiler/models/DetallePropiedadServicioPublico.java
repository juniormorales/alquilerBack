package com.back.alquiler.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DetallePropiedadServicioPublico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detallePropiedadServicioPublico;
	
	@ManyToOne
	@JoinColumn(name="id_propiedad")
	private Propiedad propiedad;
	
	@ManyToOne
	@JoinColumn(name="id_servicio_publico")
	private ServicioPublico servicioPublico;

	public Integer getDetallePropiedadServicioPublico() {
		return detallePropiedadServicioPublico;
	}

	public void setDetallePropiedadServicioPublico(Integer detallePropiedadServicioPublico) {
		this.detallePropiedadServicioPublico = detallePropiedadServicioPublico;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	public ServicioPublico getServicioPublico() {
		return servicioPublico;
	}

	public void setServicioPublico(ServicioPublico servicioPublico) {
		this.servicioPublico = servicioPublico;
	}
	
	
}
