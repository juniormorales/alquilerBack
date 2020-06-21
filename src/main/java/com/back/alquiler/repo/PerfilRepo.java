package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Perfil;


public interface  PerfilRepo extends JpaRepository<Perfil, Integer>{
	
}
