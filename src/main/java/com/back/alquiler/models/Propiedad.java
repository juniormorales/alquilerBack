package com.back.alquiler.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Propiedad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPropiedad;
	
	@Column(nullable=false)
	private String alias;
	
	@Column(nullable=false)
	private String descripcionGeneral;
	
	@Column(nullable=false)
	private String descripcionDanios;
	
	@Column(nullable=false)
	private Integer nroHabitaciones;
	
	@Column(nullable=false)
	private Integer cantidadPisos;
	
	@Column(nullable=false)
	private Date fechaRegistro;
	
	//metros cuadrados
	@Column(nullable=false)
	private Double tamano;
	
	@Column(nullable=false)
	private Boolean tieneDanios;
	
	@Column(nullable=false)
	private Boolean permiteMascotas;
	
	@Column(nullable=false)
	private String condicionPropiedad;
	
	//0: fuera de servicio, 1: disponible, 2: en mantenimiento , 3: ya ocupado
	@Column(nullable=false)
	private Integer estado;
	
	@ManyToOne
	@JoinColumn(name = "id_condicion_pago", nullable=false)
	private CondicionPago condicionPago;
	
	@ManyToOne
	@JoinColumn(name="id_arrendero", nullable=false)
	private Arrendero arrendero;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "propiedad")
	private List<ImagenPropiedad> lsImagenPropiedad;

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getDescripcionDanios() {
		return descripcionDanios;
	}

	public void setDescripcionDanios(String descripcionDanios) {
		this.descripcionDanios = descripcionDanios;
	}

	public Boolean getTieneDanios() {
		return tieneDanios;
	}

	public void setTieneDanios(Boolean tieneDanios) {
		this.tieneDanios = tieneDanios;
	}

	public List<ImagenPropiedad> getLsImagenPropiedad() {
		return lsImagenPropiedad;
	}

	public void setLsImagenPropiedad(List<ImagenPropiedad> lsImagenPropiedad) {
		this.lsImagenPropiedad = lsImagenPropiedad;
	}
	
	
	
}
