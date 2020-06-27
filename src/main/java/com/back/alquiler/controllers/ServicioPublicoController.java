package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.ServicioPublicoService;

@RestController
@RequestMapping("/api/servicio-publico")
public class ServicioPublicoController {
	
	@Autowired
	ServicioPublicoService service;
	
}
