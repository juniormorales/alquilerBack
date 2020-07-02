package com.back.alquiler.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Propiedad;
import com.back.alquiler.service.ImagenPropiedadService;
import com.back.alquiler.service.PropiedadService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/propiedad")
public class PropiedadController {

	@Autowired
	PropiedadService service_propiedad;
	
	@Autowired
	ImagenPropiedadService service_imagen_prop;
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarPorArrendero(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		try {
				List<Propiedad> lsProp = service_propiedad.listarPorArrendero(id);
				response.put("aaData",lsProp);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarPropiedadError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
	
	@PutMapping("/modificar")
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
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarPropiedad(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			try {
				service_imagen_prop.eliminarTodasLasImagenes(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Boolean resp = service_propiedad.eliminar(id);
			if (resp) {
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgEliminarOk);
				response.put("tipo",Constantes.success);

			} else {
				response.put("titulo", Constantes.tituloError);
				response.put("mensaje", Constantes.msgEliminarError);
				response.put("tipo",Constantes.error);
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		}catch(DataIntegrityViolationException e) {
			response.put("mensaje", Constantes.msgEliminarErrorGrave);
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}
