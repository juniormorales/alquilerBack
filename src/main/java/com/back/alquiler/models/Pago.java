package com.back.alquiler.models;

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
	
	@Column(nullable=false)
	private String urlVoucher;
	
	//true: si fue aceptado, false: si no
	@Column(nullable=false)
	private Boolean estado;
	
	@ManyToOne
	@JoinColumn(name="id_renta")
	private Renta renta;

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
	
	
}
