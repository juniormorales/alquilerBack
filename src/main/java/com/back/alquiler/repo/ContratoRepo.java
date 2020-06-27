package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Contrato;

public interface ContratoRepo extends JpaRepository<Contrato,Integer>{

}
