package com.back.alquiler.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="modulo")
public class Modulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_modulo")
	private Integer idModulo;

	@Column( nullable = false)
	private String title;
	
	@Column( nullable = false)
	private String icontype;
	
	//"link" si no tiene paginas, "sub" si tiene paginas
	@Column(nullable=false)
	private String type;
	
	//null si no tiene paginas, <name> si se le quiere añadir un nombre al despliegue del modulo
	@Column(nullable = true)
	private String collapse;
	
	@Column(nullable = false, length = 50)
	private String path;
	
	//false si no tiene paginas, true si tiene paginas
	@Column(nullable=false)
	private Boolean isCollapsed;
	
	@Column(name = "orden", nullable = false)
	private Integer orden;
	
	@OneToMany(mappedBy = "modulo", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Pagina> children;

	public Integer getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String descripcion) {
		this.title = descripcion;
	}

	public String getIcontype() {
		return icontype;
	}

	public void setIcontype(String icono) {
		this.icontype = icono;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String raiz) {
		this.path = raiz;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public List<Pagina> getChildren() {
		return children;
	}

	public void setChildren(List<Pagina> children) {
		this.children = children;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
}
