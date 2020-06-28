package com.back.alquiler.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SolicitudPropiedad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSolicitudPropiedad;
	
	@Column(nullable=false)
	private Date fechaSolicitud;
	
	//Pendiente:2, Rechazado:0 , Aceptado:1, Confirmado:3, Cancelado:4
	@Column(nullable=false)
	private Integer estado;
	
	//meses
	@Column(nullable=false)
	private Integer tiempoArrendamiento;
	
	@Column(nullable=false)
	private Integer nroHuespedPropuesto;
	
	@Column(nullable=true)
	private String descripcionRechazo;
	
	@ManyToOne
	@JoinColumn(name="id_arrendatario")
	private Arrendatario arrendatario;
	
	@ManyToOne
	@JoinColumn(name="id_propiedad")
	private Propiedad propiedad;

	public Integer getIdSolicitudPropiedad() {
		return idSolicitudPropiedad;
	}

	public void setIdSolicitudPropiedad(Integer idSolicitudPropiedad) {
		this.idSolicitudPropiedad = idSolicitudPropiedad;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getTiempoArrendamiento() {
		return tiempoArrendamiento;
	}

	public void setTiempoArrendamiento(Integer tiempoArrendamiento) {
		this.tiempoArrendamiento = tiempoArrendamiento;
	}

	public Integer getNroHuespedPropuesto() {
		return nroHuespedPropuesto;
	}

	public void setNroHuespedPropuesto(Integer nroHuespedPropuesto) {
		this.nroHuespedPropuesto = nroHuespedPropuesto;
	}

	public String getDescripcionRechazo() {
		return descripcionRechazo;
	}

	public void setDescripcionRechazo(String descripcionRechazo) {
		this.descripcionRechazo = descripcionRechazo;
	}

	public Arrendatario getArrendatario() {
		return arrendatario;
	}

	public void setArrendatario(Arrendatario arrendatario) {
		this.arrendatario = arrendatario;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}
}