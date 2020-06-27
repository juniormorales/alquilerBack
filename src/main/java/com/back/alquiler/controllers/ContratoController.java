package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.ContratoService;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {
	
	@Autowired
	ContratoService service_contrato;
	
}
