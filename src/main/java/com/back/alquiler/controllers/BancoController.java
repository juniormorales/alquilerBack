package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Banco;
import com.back.alquiler.service.BancoService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/banco")
public class BancoController {
	
	@Autowired
	BancoService serviceBanco;
	
	@GetMapping("/listar")
	public ResponseEntity<Map<String, Object>> listar(){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Banco> lsbancos = serviceBanco.listar();
			response.put(Constantes.AADATA_TXT_RESPONSE,lsbancos);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_BANCO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
