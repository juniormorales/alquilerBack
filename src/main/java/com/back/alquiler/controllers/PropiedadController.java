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
import com.back.alquiler.models.UbicacionMaps;
import com.back.alquiler.service.ImagenPropiedadService;
import com.back.alquiler.service.PropiedadService;
import com.back.alquiler.service.UbicacionMapService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/propiedad")
public class PropiedadController {

	@Autowired
	PropiedadService servicePropiedad;

	@Autowired
	ImagenPropiedadService serviceImagenProp;

	@Autowired
	UbicacionMapService serviceUbicacion;

	@GetMapping("/listar/{id}")
	public ResponseEntity<Map<String, Object>> listarPorArrendero(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Propiedad> lsProp = servicePropiedad.listarPorArrendero(id);
			response.put(Constantes.AADATA_TXT_RESPONSE, lsProp);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_PROPIEDAD_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarPorAceptar")
	public ResponseEntity<Map<String, Object>> listarUbicacionMapPorAceptar() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<UbicacionMaps> lsProp = servicePropiedad.listarNoAceptadas();
			response.put(Constantes.AADATA_TXT_RESPONSE, lsProp);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_PROPIEDAD_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> registrarPropiedad(@RequestBody Propiedad propiedad) {
		Map<String, Object> response = new HashMap<>();
		try {
			Propiedad resp = servicePropiedad.registrar(propiedad);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_PROPIEDAD_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
			response.put(Constantes.ID_TXT_RESPONSE, resp.getIdPropiedad());
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_PROPIEDAD_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/modificar")
	public ResponseEntity<Map<String, Object>> modificarPropiedad(@RequestBody Propiedad propiedad) {
		Map<String, Object> response = new HashMap<>();
		try {
			servicePropiedad.modificar(propiedad);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTUALIZAR_PROPIEDAD_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTUALIZAR_PROPIEDAD_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Object>> eliminarPropiedad(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			try {
				serviceImagenProp.eliminarTodasLasImagenes(id);
			} catch (IOException e) {
				response.put(Constantes.MENSAJE_TXT_RESPONSE, e.getMessage());
				response.put(Constantes.ERROR_TXT_RESPONSE, Constantes.MSG_ELIMINAR_ERROR_GRAVE);
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Boolean resp = servicePropiedad.eliminar(id);
			if (resp) {
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ELIMINAR_OK);
				response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);

			} else {
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_ERROR);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ELIMINAR_ERRROR);
				response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.ERROR);
			}
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (DataIntegrityViolationException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ELIMINAR_ERROR_GRAVE);
			response.put(Constantes.ERROR_TXT_RESPONSE, Constantes.MSG_ELIMINAR_ERROR_GRAVE_DESC);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
