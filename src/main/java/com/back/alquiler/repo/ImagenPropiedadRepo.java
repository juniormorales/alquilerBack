package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.ImagenPropiedad;
import com.back.alquiler.models.Propiedad;

public interface ImagenPropiedadRepo extends JpaRepository<ImagenPropiedad,Integer>{
	
	List<ImagenPropiedad> findByPropiedad(Propiedad propiedad);
	ImagenPropiedad findByIdImagenPropiedad(Integer id);
}
