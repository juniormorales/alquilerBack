package com.back.alquiler.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_perfil")
	private Integer idPerfil;
	
	@Column(name="nombres", nullable = false, length=50)
	private String nombres;
	
	@Column(name="ambito", nullable = false)
	private Integer ambito;
	
	@Column(name="estado", nullable = false)
	private Boolean estado;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "perfiles_modulo", joinColumns = @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil"), inverseJoinColumns = @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo"))
	private List<Modulo> lsmodulos;

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Integer getAmbito() {
		return ambito;
	}

	public void setAmbito(Integer ambito) {
		this.ambito = ambito;
	}


	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public List<Modulo> getLsmodulos() {
		return lsmodulos;
	}

	public void setLsmodulos(List<Modulo> lsmodulos) {
		this.lsmodulos = lsmodulos;
	}
	
}
