package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Distrito;
import com.back.alquiler.models.Provincia;
import com.back.alquiler.service.DistritoService;
import com.back.alquiler.utils.Constantes;


@RestController
@RequestMapping("/public/distrito")
public class DistritoController {

	@Autowired
	DistritoService service;
	
	@PostMapping("/porProvincia")
	public ResponseEntity<Map<String,Object>> listarPorProvincia(@RequestBody Provincia prov) throws Exception {
		Map<String,Object> response = new HashMap<>();
		try {
			List<Distrito> lsdist = service.listarPorProvincia(prov);			
			response.put(Constantes.AADATA_TXT_RESPONSE,lsdist);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_LISTAR_DISTRIT_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

