package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.UbicacionMaps;
import com.back.alquiler.service.UbicacionMapService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/maps")
public class UbicacionMapsController {
	
	@Autowired
	UbicacionMapService service;
	
	@PostMapping("/registrarMaps")
	public ResponseEntity<?> registrarMap(@RequestBody UbicacionMaps maps){
		Map<String,Object> response = new HashMap<>();
		try {
			service.registrar(maps);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarUsuarioError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping("/listarDisponibles")
	public ResponseEntity<?> listarDisponibles(){
		Map<String,Object> response = new HashMap<>();
		try {
			List<UbicacionMaps> maps = service.listarPropiedadesDisponibles();
			response.put("aaData",maps);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		} catch(DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarUsuarioError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}
