package com.back.alquiler.dto;

public class Dato1 {
	
	Integer id_persona;
	String nombres;
	String apellidos;
	String genero;
	Integer edad;
	String email;
	String dni;
	Dato2 usuario;
	public Integer getId_persona() {
		return id_persona;
	}
	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Dato2 getUsuario() {
		return usuario;
	}
	public void setUsuario(Dato2 usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Dato1 [id_persona=" + id_persona + ", nombres=" + nombres + ", apellidos=" + apellidos + ", genero="
				+ genero + ", edad=" + edad + ", email=" + email + ", dni=" + dni + ", usuario=" + usuario + "]";
	}
	
	
}
