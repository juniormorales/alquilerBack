package com.back.alquiler.service;

import com.back.alquiler.models.Perfil;
import com.back.alquiler.models.Usuario;

public interface UsuarioService extends ICRUD<Usuario>{
	
	public Usuario findbyUsername(String username);
	public Usuario findbyPerfil(Perfil perfil);
}
