package com.back.alquiler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Inquilino;

public interface InquilinoRepo extends JpaRepository<Inquilino,Integer>{

}