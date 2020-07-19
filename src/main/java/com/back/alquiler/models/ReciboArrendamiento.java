package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReciboArrendamiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReciboArrendamiento;
	
	@Column(nullable=false)
	private String urlRecibo;
	
	@ManyToOne
	@JoinColumn(name="id_renta", nullable=false)
	private Renta renta;
	
	@ManyToOne
	@JoinColumn(name="id_pago",nullable=false)
	private Pago pago;

	public Integer getIdReciboArrendamiento() {
		return idReciboArrendamiento;
	}

	public void setIdReciboArrendamiento(Integer idReciboArrendamiento) {
		this.idReciboArrendamiento = idReciboArrendamiento;
	}

	public String getUrlRecibo() {
		return urlRecibo;
	}

	public void setUrlRecibo(String urlRecibo) {
		this.urlRecibo = urlRecibo;
	}

	public Renta getRenta() {
		return renta;
	}

	public void setRenta(Renta renta) {
		this.renta = renta;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
	
}
