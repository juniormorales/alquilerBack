package com.back.alquiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.back.alquiler.models.CondicionPago;
import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Propiedad;
import com.back.alquiler.service.ContratoService;

@SpringBootApplication
public class AlquilerBackApplication {
	
	@Autowired
	ContratoService service;
	
	public static void main(String[] args){
		SpringApplication.run(AlquilerBackApplication.class, args);
	}
}
