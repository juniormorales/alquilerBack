package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Usuario;

public interface ArrenderoRepo extends JpaRepository<Arrendero,Integer> {
	
	Arrendero findByUsuario(Usuario user);
}
