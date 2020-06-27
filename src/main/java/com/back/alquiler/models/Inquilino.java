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
public class Inquilino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInquilino;
	
	@Column(nullable=false)
	private Date fechaConfirmacion;
	
	//true: activo , false: dado de baja
	@Column(nullable=false)
	private Boolean estado;
	
	//true: tiene contrato, false: no tiene aun
	@Column(nullable=false)
	private Boolean contratoHecho;
	
	//true:al dia, false: atrasado
	@Column(nullable=false)
	private Boolean estadoPago;
	
	@ManyToOne
	@JoinColumn(name="id_solicitud_propiedad")
	private SolicitudPropiedad solicitudPropieda;

	public Integer getIdInquilino() {
		return idInquilino;
	}

	public void setIdInquilino(Integer idInquilino) {
		this.idInquilino = idInquilino;
	}

	public Date getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(Date fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Boolean getContratoHecho() {
		return contratoHecho;
	}

	public void setContratoHecho(Boolean contratoHecho) {
		this.contratoHecho = contratoHecho;
	}

	public Boolean getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(Boolean estadoPago) {
		this.estadoPago = estadoPago;
	}

	public SolicitudPropiedad getSolicitudPropieda() {
		return solicitudPropieda;
	}

	public void setSolicitudPropieda(SolicitudPropiedad solicitudPropieda) {
		this.solicitudPropieda = solicitudPropieda;
	}	
}
