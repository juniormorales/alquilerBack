package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.PagoService;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
	
	@Autowired
	PagoService service_pago;
	
}
