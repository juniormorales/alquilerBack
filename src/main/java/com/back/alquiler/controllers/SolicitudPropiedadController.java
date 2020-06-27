package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.SolicitudPropiedadService;

@RestController
@RequestMapping("/api/sol-prop")
public class SolicitudPropiedadController {
	
	@Autowired
	SolicitudPropiedadService service_sol_prop;
	
}
