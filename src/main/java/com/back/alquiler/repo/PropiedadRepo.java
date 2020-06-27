package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Propiedad;

public interface PropiedadRepo extends JpaRepository<Propiedad,Integer>{

}
