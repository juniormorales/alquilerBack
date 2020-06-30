package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.CondicionPago;
import com.back.alquiler.service.CondicionPagoService;

@RestController
@RequestMapping("/api/condicion-pago")
public class CondicionPagoController {
	
	@Autowired
	CondicionPagoService service_condicion_pago;
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarCondicion(@RequestBody CondicionPago condicionPago){
		return null;
	}
	
	@PutMapping("/modificar")
	public ResponseEntity<?> modificarCondicion(@RequestBody CondicionPago condicionPago){
		return null;
	}
	
}
