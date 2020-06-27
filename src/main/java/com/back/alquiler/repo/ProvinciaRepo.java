package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Departamento;
import com.back.alquiler.models.Provincia;



public interface ProvinciaRepo extends JpaRepository<Provincia, Integer>{

	List<Provincia> findByDepartamento(Departamento depa);

}
