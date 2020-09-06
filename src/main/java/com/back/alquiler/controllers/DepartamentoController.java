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

import com.back.alquiler.models.Departamento;
import com.back.alquiler.service.DepartamentoService;
import com.back.alquiler.utils.Constantes;



@RestController
@RequestMapping("/public/departamento")
public class DepartamentoController {

	@Autowired
	DepartamentoService service;
	
	@GetMapping("/listar")
	public ResponseEntity<Map<String,Object>> listar() throws Exception {
		Map<String,Object> response = new HashMap<>();

		try {
			List<Departamento> lsdepa = service.listar();
			response.put(Constantes.AADATA_TXT_RESPONSE,lsdepa);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_LISTAR_DEP_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}