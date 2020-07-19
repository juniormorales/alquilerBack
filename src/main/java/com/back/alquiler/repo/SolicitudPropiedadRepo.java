package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Propiedad;
import com.back.alquiler.models.SolicitudPropiedad;

public interface SolicitudPropiedadRepo extends JpaRepository<SolicitudPropiedad,Integer>{
	
	List<SolicitudPropiedad> findByArrendero(Arrendero arrendero);
	List<SolicitudPropiedad> findByArrendatario(Arrendatario arrendatario);
	SolicitudPropiedad findByPropiedad(Propiedad propiedad);
	
	SolicitudPropiedad findByArrendatarioAndPropiedadOrderByFechaSolicitudDesc(Arrendatario arrendatario, Propiedad propiedad);
}
