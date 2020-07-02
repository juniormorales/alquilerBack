package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.CondicionPago;

public interface CondicionPagoRepo extends JpaRepository<CondicionPago,Integer> {
	
	List<CondicionPago> findByArrendero(Arrendero arrendero);
}
