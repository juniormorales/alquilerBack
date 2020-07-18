package com.back.alquiler.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Pago;
import com.back.alquiler.models.Propiedad;
import com.back.alquiler.service.PagoService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
	
	@Autowired
	PagoService service_pago;
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarPago(@RequestBody Pago pago) {
		Map<String, Object> response = new HashMap<>();
		try {
			service_pago.registrar(pago);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgRegistrarPagoOk);
			response.put("tipo", Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/modificar")
	public ResponseEntity<?> modificarPago(@RequestBody Pago pago) {
		Map<String, Object> response = new HashMap<>();
		try {
				service_pago.modificar(pago);
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgActualizarPagoOk);
				response.put("tipo",Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgActualizarPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
