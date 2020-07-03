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
public class Contrato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idContrato;
	
	@Column(nullable=false)
	private Date fechaEmision;
	
	@Column(nullable=false)
	private Date fechaInicio;
	
	//mesese
	@Column(nullable=false)
	private Integer tiempoContrato;
	
	@Column(nullable=true)
	private String rutaContrato;
	
	@Column(nullable=false)
	private Boolean caduco;
	
	@ManyToOne
	@JoinColumn(name="id_inquilino")
	private Inquilino inquilino;

	public Integer getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getTiempoContrato() {
		return tiempoContrato;
	}

	public void setTiempoContrato(Integer tiempoContrato) {
		this.tiempoContrato = tiempoContrato;
	}

	public String getRutaContrato() {
		return rutaContrato;
	}

	public void setRutaContrato(String rutaContrato) {
		this.rutaContrato = rutaContrato;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public Boolean getCaduco() {
		return caduco;
	}

	public void setCaduco(Boolean caduco) {
		this.caduco = caduco;
	}
}
