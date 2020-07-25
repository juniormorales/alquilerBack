package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Banco;

public interface BancoRepo extends JpaRepository<Banco, Integer> {
	
	
}
