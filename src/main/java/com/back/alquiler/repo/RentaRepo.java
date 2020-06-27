package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Renta;

public interface RentaRepo extends JpaRepository<Renta,Integer> {

}
