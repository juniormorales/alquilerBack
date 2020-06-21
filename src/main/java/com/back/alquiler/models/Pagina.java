package com.back.alquiler.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pagina")
public class Pagina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pagina")
	private Integer idPagina;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String path;

	@Column(nullable = false)
	private String smallTitle;

	@Column(nullable = false)
	private String type;

	// null si no tiene paginas, <name> si se le quiere añadir un nombre al
	// despliegue del modulo
	@Column(nullable = true)
	private String collapse;

	// false si no tiene paginas, true si tiene paginas
	@Column(nullable = false)
	private Boolean isCollapsed;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_modulo", nullable = false)
	private Modulo modulo;

	public Integer getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(Integer idPagina) {
		this.idPagina = idPagina;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String descripcion) {
		this.title = descripcion;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String url) {
		this.path = url;
	}

	public String getSmallTitle() {
		return smallTitle;
	}

	public void setSmallTitle(String parametros) {
		this.smallTitle = parametros;
	}

	public String getType() {
		return type;
	}

	public void setType(String icono) {
		this.type = icono;
	}

	public String getCollapse() {
		return collapse;
	}

	public void setCollapse(String collapse) {
		this.collapse = collapse;
	}

	public Boolean getIsCollapsed() {
		return isCollapsed;
	}

	public void setIsCollapsed(Boolean isCollapsed) {
		this.isCollapsed = isCollapsed;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

}
