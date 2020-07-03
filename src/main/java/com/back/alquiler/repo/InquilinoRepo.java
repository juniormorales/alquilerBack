package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Inquilino;

public interface InquilinoRepo extends JpaRepository<Inquilino,Integer>{
	
	List<Inquilino> findByArrenderoAndContratoHechoAndEstado(Arrendero arrendero, Boolean contrato, Boolean estado);
}
