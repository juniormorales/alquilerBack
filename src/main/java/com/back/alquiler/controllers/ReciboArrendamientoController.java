package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.ReciboArrendamientoService;

@RestController
@RequestMapping("/api/recibo-arrend")
public class ReciboArrendamientoController {
	
	@Autowired
	ReciboArrendamientoService service;
	
}
