package com.back.alquiler.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.back.alquiler.dto.ImagenPropiedadDTO;
import com.back.alquiler.service.ImagenPropiedadService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/img-prop")
public class ImagenPropiedadController {

	@Autowired
	ImagenPropiedadService service;

	@PostMapping("/uploadImage")
	public ResponseEntity<?> subirImagen(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			service.registrarImagen(id, archivo);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgRegistrarImagenPropiedadOk);
			response.put("tipo", Constantes.success);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.errorRegistroFoto);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			response.put("mensaje", Constantes.errorLecturaFoto);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/eliminarImagenes")
	public ResponseEntity<?> eliminarImagenes(@RequestBody ImagenPropiedadDTO dto) {
		Map<String, Object> response = new HashMap<>();

		try {
			service.eliminarImagenes(dto.getLsIds());
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgEliminarImagenesPropiedadOk);
			response.put("tipo", Constantes.success);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgEliminarImagenesPropiedadError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			response.put("mensaje", Constantes.errorLecturaFoto);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarImagenes/{id}")
	public ResponseEntity<?> listarImagenes(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Map<String, Object>> lsMaps = service.retornarImagenes(id);
			response.put("aaData", lsMaps);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarImagenesPropiedadError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			response.put("mensaje", Constantes.errorLecturaFoto);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
