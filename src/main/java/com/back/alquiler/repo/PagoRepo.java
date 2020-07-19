package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Pago;

public interface PagoRepo extends JpaRepository<Pago,Integer> {
	
	List<Pago> findByArrenderoAndEstado(Arrendero arrendero, Boolean estado);
}
