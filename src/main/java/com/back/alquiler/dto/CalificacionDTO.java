package com.back.alquiler.dto;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Arrendero;

public class CalificacionDTO {
	
	private Integer calificacion;
	
	private String comentario;
	
	private Arrendatario arrendatario;

	private Arrendero arrendero;

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Arrendatario getArrendatario() {
		return arrendatario;
	}

	public void setArrendatario(Arrendatario arrendatario) {
		this.arrendatario = arrendatario;
	}

	public Arrendero getArrendero() {
		return arrendero;
	}

	public void setArrendero(Arrendero arrendero) {
		this.arrendero = arrendero;
	}
}
