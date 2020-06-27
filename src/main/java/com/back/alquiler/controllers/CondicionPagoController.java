package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.CondicionPagoService;

@RestController
@RequestMapping("/api/condicion-pago")
public class CondicionPagoController {
	
	@Autowired
	CondicionPagoService service_condicion_pago;
	
}
