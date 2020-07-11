package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Propiedad;

public interface PropiedadRepo extends JpaRepository<Propiedad,Integer>{
	
	List<Propiedad> findByArrenderoAndConfirmado(Arrendero arrendero, Boolean confirmado);
	
	List<Propiedad> findByConfirmadoAndRechazado(Boolean confirmado, Boolean rechazado);
}
