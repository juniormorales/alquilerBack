package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Contrato;
import com.back.alquiler.models.Inquilino;

public interface ContratoRepo extends JpaRepository<Contrato,Integer>{
	
	Contrato findByInquilinoAndCaduco(Inquilino inquilino, Boolean caduco);
}
