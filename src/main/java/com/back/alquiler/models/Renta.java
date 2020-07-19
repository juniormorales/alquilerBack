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
public class Renta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRenta;
	
	@Column(nullable=false)
	private Date fechaRenta;
	
	@Column(nullable=false)
	private Double cantidad;
	
	@Column(nullable=false)
	private Double montoAcumuladoCancelado;
	
	//1: cancelado, 0: proximo, 2: vencido
	@Column(nullable=false)
	private Integer estado;
	
	//0: pago enviado, 1:pago rechazado, 2: sin enviar, 3: incompleto
	@Column(nullable=false)
	private Integer envioPago;
	
	@Column(nullable=false)
	private Double importeAtrasado;
	
	@ManyToOne
	@JoinColumn(name="id_inquilino")
	private Inquilino inquilino;

	public Integer getIdRenta() {
		return idRenta;
	}

	public void setIdRenta(Integer idRenta) {
		this.idRenta = idRenta;
	}

	public Date getFechaRenta() {
		return fechaRenta;
	}

	public void setFechaRenta(Date fechaRenta) {
		this.fechaRenta = fechaRenta;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Double getImporteAtrasado() {
		return importeAtrasado;
	}

	public void setImporteAtrasado(Double importeAtrasado) {
		this.importeAtrasado = importeAtrasado;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public Integer getEnvioPago() {
		return envioPago;
	}

	public void setEnvioPago(Integer envioPago) {
		this.envioPago = envioPago;
	}

	public Double getMontoAcumuladoCancelado() {
		return montoAcumuladoCancelado;
	}

	public void setMontoAcumuladoCancelado(Double montoAcumuladoCancelado) {
		this.montoAcumuladoCancelado = montoAcumuladoCancelado;
	}
	
}
