package com.back.alquiler.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Arrendero {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idArrendero;
	
	@Column(nullable=false)
	private String direccionActual;
	
	@Column(nullable=false)
	private Integer nroPartidaRegistral;
	
	@OneToOne
	@JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="id_provincia")
	private Provincia provincia;
	
	@ManyToOne
	@JoinColumn(name="id_distrito")
	private Distrito distrito;

	public Integer getIdArrendero() {
		return idArrendero;
	}

	public void setIdArrendero(Integer idArrendero) {
		this.idArrendero = idArrendero;
	}

	public String getDireccionActual() {
		return direccionActual;
	}

	public void setDireccionActual(String direccionActual) {
		this.direccionActual = direccionActual;
	}

	public Integer getNroPartidaRegistral() {
		return nroPartidaRegistral;
	}

	public void setNroPartidaRegistral(Integer nroPartidaRegistral) {
		this.nroPartidaRegistral = nroPartidaRegistral;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}
	
	
}
