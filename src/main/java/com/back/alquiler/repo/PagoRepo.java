package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Pago;

public interface PagoRepo extends JpaRepository<Pago,Integer> {

}
