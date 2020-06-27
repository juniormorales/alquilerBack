package com.back.alquiler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Distrito;
import com.back.alquiler.models.Provincia;



public interface DistritoRepo extends JpaRepository<Distrito, Integer>{

	List<Distrito> findByProvincia(Provincia prov);

}
