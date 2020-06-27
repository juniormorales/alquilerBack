package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.CalificacionService;

@RestController
@RequestMapping("/api/calificacion")
public class CalificacionController {
	
	@Autowired
	CalificacionService service_calificacion;
	
}
