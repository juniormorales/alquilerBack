package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.ArrendatarioService;

@RestController
@RequestMapping("/api/arrendatario")
public class ArrendatarioController {
	
	@Autowired
	ArrendatarioService service_arrendatario;
	
	
}
