package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Perfil;
import com.back.alquiler.models.Usuario;

public interface UsuarioService extends ICRUD<Usuario>{
	
	public Usuario findbyUsername(String username);
	public Usuario findbyPerfil(Perfil perfil);
	public Boolean buscarSiExisteEmail(String email);
	public Boolean buscarSiExisteUsername(String username);
	public Boolean buscarSiExisteDNI(String dni);
	public Usuario inhabilitarCuenta(Usuario usuario);
	public Usuario activarCuenta(Usuario usuario);
	public List<Arrendero> listarCuentasNoActivadas();
	public Boolean verificarInhabilitado(Usuario usuario);
}
