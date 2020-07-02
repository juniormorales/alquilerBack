package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Usuario;

public interface ArrendatarioRepo extends JpaRepository<Arrendatario, Integer> {
	
	Arrendatario findByUsuario(Usuario usuario);
}
