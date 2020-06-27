package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Departamento;



public interface DepartamentoRepo extends JpaRepository<Departamento, Integer>{

}
