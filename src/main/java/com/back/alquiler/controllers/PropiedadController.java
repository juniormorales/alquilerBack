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

import com.back.alquiler.models.Propiedad;
import com.back.alquiler.service.PropiedadService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/propiedad")
public class PropiedadController {

	@Autowired
	PropiedadService service_propiedad;

	@PostMapping("/registrar")
	public ResponseEntity<?> registrarPropiedad(@RequestBody Propiedad propiedad) {
		Map<String, Object> response = new HashMap<>();
		try {
				service_propiedad.registrar(propiedad);
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgRegistrarPropiedadOk);
				response.put("tipo",Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarPropiedadError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/modificar")
	public ResponseEntity<?> modificarPropiedad(@RequestBody Propiedad propiedad) {
		Map<String, Object> response = new HashMap<>();
		try {
				service_propiedad.modificar(propiedad);
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgActualizarPropiedadOk);
				response.put("tipo",Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgActualizarPropiedadError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
