package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendero;

public interface ArrenderoRepo extends JpaRepository<Arrendero,Integer> {

}
