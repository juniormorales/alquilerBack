package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Renta;

public interface RentaRepo extends JpaRepository<Renta,Integer> {
	
	List<Renta> findByInquilinoAndEstado(Inquilino inquilino, Integer estado);
}
