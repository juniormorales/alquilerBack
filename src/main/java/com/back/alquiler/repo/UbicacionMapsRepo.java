package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Propiedad;
import com.back.alquiler.models.UbicacionMaps;

public interface UbicacionMapsRepo extends JpaRepository<UbicacionMaps,Integer>{
	
	void deleteByPropiedad(Propiedad propiedad);
	
	UbicacionMaps findByPropiedad(Propiedad propiedad);
}
