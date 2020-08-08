package com.back.alquiler.dto;

public class Dato2 {

	String username;
	String password;
	String token;
	Integer id_usuario;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	@Override
	public String toString() {
		return "Dato2 [username=" + username + ", password=" + password + ", token=" + token + ", id_usuario="
				+ id_usuario + "]";
	}
	
	
	
}
