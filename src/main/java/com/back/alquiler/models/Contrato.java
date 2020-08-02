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
	private Date fechaFin;
	
	@Column(nullable=true)
	private String archivoContrato;
	
	@Column(nullable=false)
	private Boolean caduco;
	
	@Column(nullable=true)
	private Double garantia;
	
	@ManyToOne
	@JoinColumn(name="id_inquilino")
	private Inquilino inquilino;
	
	@ManyToOne
	@JoinColumn(name="id_arrendero",nullable=true)
	private Arrendero arrendero;

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

	public String getArchivoContrato() {
		return archivoContrato;
	}

	public void setArchivoContrato(String rutaContrato) {
		this.archivoContrato = rutaContrato;
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

	public Double getGarantia() {
		return garantia;
	}

	public void setGarantia(Double garantia) {
		this.garantia = garantia;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Arrendero getArrendero() {
		return arrendero;
	}

	public void setArrendero(Arrendero arrendero) {
		this.arrendero = arrendero;
	}
}
