package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Propiedad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPropiedad;
	
	@Column(nullable=false)
	private String descripcionGeneral;
	
	@Column(nullable=false)
	private String descripcionDaños;
	
	@Column(nullable=false)
	private Integer nroHabitaciones;
	
	@Column(nullable=false)
	private Integer cantidadPisos;
	
	//metros cuadrados
	@Column(nullable=false)
	private Double tamano;
	
	@Column(nullable=false)
	private Boolean permiteMascotas;
	
	@Column(nullable=false)
	private String condicionPropiedad;
	
	@Column(nullable=false)
	private Boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "id_condicion_pago")
	private CondicionPago condicionPago;
	
	@ManyToOne
	@JoinColumn(name="id_arrendero")
	private Arrendero arrendero;

	public Integer getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(Integer idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public String getDescripcionGeneral() {
		return descripcionGeneral;
	}

	public void setDescripcionGeneral(String descripcionGeneral) {
		this.descripcionGeneral = descripcionGeneral;
	}

	public String getDescripcionDaños() {
		return descripcionDaños;
	}

	public void setDescripcionDaños(String descripcionDaños) {
		this.descripcionDaños = descripcionDaños;
	}

	public Integer getNroHabitaciones() {
		return nroHabitaciones;
	}

	public void setNroHabitaciones(Integer nroHabitaciones) {
		this.nroHabitaciones = nroHabitaciones;
	}

	public Integer getCantidadPisos() {
		return cantidadPisos;
	}

	public void setCantidadPisos(Integer cantidadPisos) {
		this.cantidadPisos = cantidadPisos;
	}

	public Double getTamano() {
		return tamano;
	}

	public void setTamano(Double tamano) {
		this.tamano = tamano;
	}

	public Boolean getPermiteMascotas() {
		return permiteMascotas;
	}

	public void setPermiteMascotas(Boolean permiteMascotas) {
		this.permiteMascotas = permiteMascotas;
	}

	public String getCondicionPropiedad() {
		return condicionPropiedad;
	}

	public void setCondicionPropiedad(String condicionPropiedad) {
		this.condicionPropiedad = condicionPropiedad;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public CondicionPago getCondicionPago() {
		return condicionPago;
	}

	public void setCondicionPago(CondicionPago condicionPago) {
		this.condicionPago = condicionPago;
	}

	public Arrendero getArrendero() {
		return arrendero;
	}

	public void setArrendero(Arrendero arrendero) {
		this.arrendero = arrendero;
	}
}
