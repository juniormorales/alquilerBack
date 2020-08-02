package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CondicionPago {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCondicionPago;
	
	@Column(nullable=false)
	private String alias;
	
	@Column(nullable=false)
	private Double precio;
	
	//En meses
	@Column(nullable=false)
	private Integer tiempoMinContrato;
	
	@Column
	private Double montoMinGarantia;
	
	@Column
	private Double montoMaxGarantia;
	
	@Column
	private Integer diaMesCobro;
	
	@Column(nullable=false)
	private Boolean responsabilidadReparar;
	
	@Column(nullable=false)
	private Double tasaRecargo;
	
	@Column(nullable=false)
	private Double penalidadNoDesocupar;
	
	@ManyToOne
	@JoinColumn(name="id_arrendero",nullable=false)
	private Arrendero arrendero;

	public Integer getIdCondicionPago() {
		return idCondicionPago;
	}

	public void setIdCondicionPago(Integer idCondicionPago) {
		this.idCondicionPago = idCondicionPago;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getTiempoMinContrato() {
		return tiempoMinContrato;
	}

	public void setTiempoMinContrato(Integer tiempoMinContrato) {
		this.tiempoMinContrato = tiempoMinContrato;
	}

	public Double getMontoMinGarantia() {
		return montoMinGarantia;
	}

	public void setMontoMinGarantia(Double montoMinGarantia) {
		this.montoMinGarantia = montoMinGarantia;
	}

	public Double getMontoMaxGarantia() {
		return montoMaxGarantia;
	}

	public void setMontoMaxGarantia(Double montoMaxGarantia) {
		this.montoMaxGarantia = montoMaxGarantia;
	}

	public Integer getDiaMesCobro() {
		return diaMesCobro;
	}

	public void setDiaMesCobro(Integer diaMesCobro) {
		this.diaMesCobro = diaMesCobro;
	}

	public Boolean getResponsabilidadReparar() {
		return responsabilidadReparar;
	}

	public void setResponsabilidadReparar(Boolean responsabilidadReparar) {
		this.responsabilidadReparar = responsabilidadReparar;
	}

	public Double getTasaRecargo() {
		return tasaRecargo;
	}

	public void setTasaRecargo(Double tasaRecargo) {
		this.tasaRecargo = tasaRecargo;
	}

	public Arrendero getArrendero() {
		return arrendero;
	}

	public void setArrendero(Arrendero arrendero) {
		this.arrendero = arrendero;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Double getPenalidadNoDesocupar() {
		return penalidadNoDesocupar;
	}

	public void setPenalidadNoDesocupar(Double penalidadNoDesocupar) {
		this.penalidadNoDesocupar = penalidadNoDesocupar;
	}	
	
	
}
