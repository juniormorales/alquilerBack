package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.service.ContratoService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {
	
	@Autowired
	ContratoService service_contrato;
	
	@PostMapping("/habilitarContrato")
	public ResponseEntity<?> registrarContrato(@RequestBody Inquilino inquilino){
		Map<String,Object> response = new HashMap<>();
		try {
			service_contrato.habilitarContrato(inquilino);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgCrearContratoOk);
			response.put("tipo", Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgHabilitarContratoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
