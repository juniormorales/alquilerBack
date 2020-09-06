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

import com.back.alquiler.dto.JsonGeneral;
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
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_UBICACION_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping("/listarDisponibles")
	public ResponseEntity<Map<String, Object>> listarDisponibles(){
		Map<String,Object> response = new HashMap<>();
		try {
			List<UbicacionMaps> maps = service.listarPropiedadesDisponibles();
			response.put(Constantes.AADATA_TXT_RESPONSE,maps);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		} catch(DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_PROPDISPONIBLE_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/filtrarDisponibles")
	public ResponseEntity<Map<String, Object>> filtrarDisponibles(@RequestBody List<JsonGeneral> filtros){
		Map<String,Object> response = new HashMap<>();
		try {
			List<UbicacionMaps> maps = service.filtrarPropiedadesDisponibleS(filtros);
			response.put(Constantes.AADATA_TXT_RESPONSE,maps);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		} catch(DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_FILTRAR_PROP_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	
}
