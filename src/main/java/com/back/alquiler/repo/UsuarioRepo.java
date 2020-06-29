package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Perfil;
import com.back.alquiler.models.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	public Usuario findByUsername(String username);

	public Usuario findByPerfil(Perfil perfil);
	
	public Boolean existsByEmail(String email);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByDni(String dni);
}
