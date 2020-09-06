package com.back.alquiler.controllers;

import java.io.IOException;
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
import com.back.alquiler.utils.ImagenErrorResponse;

@RestController
@RequestMapping("/api/img-prop")
public class ImagenPropiedadController {

	@Autowired
	ImagenPropiedadService service;

	@PostMapping("/uploadImage")
	public ResponseEntity<Map<String, Object>> subirImagen(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			service.registrarImagen(id, archivo);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_IMAGENPROP_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			return ImagenErrorResponse.errorRegistro(e);
		} catch (IOException e) {
			return ImagenErrorResponse.errorLectura(e);
		}
	}

	@PostMapping("/eliminarImagenes")
	public ResponseEntity<Map<String, Object>> eliminarImagenes(@RequestBody ImagenPropiedadDTO dto) {
		Map<String, Object> response = new HashMap<>();

		try {
			service.eliminarImagenes(dto.getLsIds());
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ELIMINAR_IMAGENPROP_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ELIMINAR_IMAGENPROP_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			return ImagenErrorResponse.errorLectura(e);
		}
	}

	@GetMapping("/listarImagenes/{id}")
	public ResponseEntity<Map<String, Object>> listarImagenes(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Map<String, Object>> lsMaps = service.retornarImagenes(id);
			response.put(Constantes.AADATA_TXT_RESPONSE, lsMaps);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_IMAGENPROP_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			return ImagenErrorResponse.errorLectura(e);
		}
	}
}
