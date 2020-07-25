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
public class Pago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPago;
	
	@Column(nullable=false)
	private Double monto;
	
	@Column(nullable=true)
	private Double montoRestante;
	
	@Column(nullable=false)
	private String urlVoucher;
	
	//true: si fue aceptado, false: si esta por confirmar
	@Column(nullable=false)
	private Boolean estado;
	
	@Column(nullable=false)
	private Boolean rechazado;
	
	@Column(nullable=false)
	private Date fechaRegistro;
	
	@Column(nullable=true)
	private Date fechaConfirmado;
	
	@Column(nullable=false)
	private String banco;
	
	@Column(nullable=true)
	private String nro_boleta;
	
	@Column(nullable=true)
	private String montoLetras;
	
	@ManyToOne
	@JoinColumn(name="id_renta", nullable=false)
	private Renta renta;
	
	@ManyToOne
	@JoinColumn(name="id_arrendero",nullable=false)
	private Arrendero arrendero;
	
	@ManyToOne
	@JoinColumn(name="id_inquilino",nullable=false)
	private Inquilino inquilino;

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getUrlVoucher() {
		return urlVoucher;
	}

	public void setUrlVoucher(String urlVoucher) {
		this.urlVoucher = urlVoucher;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Renta getRenta() {
		return renta;
	}

	public void setRenta(Renta renta) {
		this.renta = renta;
	}

	public Arrendero getArrendero() {
		return arrendero;
	}

	public void setArrendero(Arrendero arrendero) {
		this.arrendero = arrendero;
	}

	public Boolean getRechazado() {
		return rechazado;
	}

	public void setRechazado(Boolean rechazado) {
		this.rechazado = rechazado;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public Double getMontoRestante() {
		return montoRestante;
	}

	public void setMontoRestante(Double montoRestante) {
		this.montoRestante = montoRestante;
	}


	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNro_boleta() {
		return nro_boleta;
	}

	public void setNro_boleta(String nro_boleta) {
		this.nro_boleta = nro_boleta;
	}

	public Date getFechaConfirmado() {
		return fechaConfirmado;
	}

	public void setFechaConfirmado(Date fechaConfirmado) {
		this.fechaConfirmado = fechaConfirmado;
	}

	public String getMontoLetras() {
		return montoLetras;
	}

	public void setMontoLetras(String montoLetras) {
		this.montoLetras = montoLetras;
	}
}
