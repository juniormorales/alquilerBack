package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.RentaService;

@RestController
@RequestMapping("/api/renta")
public class RentaController {
	
	@Autowired
	RentaService service_renta;
	
}
