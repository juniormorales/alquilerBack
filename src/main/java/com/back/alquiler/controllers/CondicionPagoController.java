package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.CondicionPago;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.service.CondicionPagoService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/condicion-pago")
public class CondicionPagoController {

	@Autowired
	CondicionPagoService service_condicion_pago;

	@PostMapping("/registrar")
	public ResponseEntity<?> registrarCondicion(@RequestBody CondicionPago condicionPago) {
		Map<String, Object> response = new HashMap<>();
		try {
	
				CondicionPago resp = service_condicion_pago.registrar(condicionPago);
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgRegistrarCondicionPagoOk);
				response.put("tipo", Constantes.success);
				response.put("defaultObj", resp);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarCondicionPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/modificar")
	public ResponseEntity<?> modificarCondicion(@RequestBody CondicionPago condicionPago) {
		return null;
	}
	

}
