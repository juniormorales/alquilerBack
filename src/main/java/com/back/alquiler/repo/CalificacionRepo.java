package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Calificacion;

public interface CalificacionRepo extends JpaRepository<Calificacion,Integer>{
	
	List<Calificacion> findByArrendatario(Arrendatario arrendatario);
}
