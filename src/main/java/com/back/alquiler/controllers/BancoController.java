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
	BancoService service;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Banco> lsbancos = service.listar();
			response.put("aaData",lsbancos);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje", Constantes.msgListarBancosError);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
