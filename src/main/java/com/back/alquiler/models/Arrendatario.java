package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Arrendatario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idArrendatario;
	
	@Column(nullable=false)
	private String direccionTemporal;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Integer getIdArrendatario() {
		return idArrendatario;
	}

	public void setIdArrendatario(Integer idArrendatario) {
		this.idArrendatario = idArrendatario;
	}

	public String getDireccionTemporal() {
		return direccionTemporal;
	}

	public void setDireccionTemporal(String direccionTemporal) {
		this.direccionTemporal = direccionTemporal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
