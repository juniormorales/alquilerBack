package com.back.alquiler.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.dto.CalificacionDTO;
import com.back.alquiler.models.Calificacion;
import com.back.alquiler.service.CalificacionService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/calificacion")
public class CalificacionController {
	
	@Autowired
	CalificacionService serviceCalificacion;
	
	@GetMapping("/listarCalificacionPorArrendatario/{id}")
	public ResponseEntity<Map<String, Object>> listarCalificacionesArrendatario(@PathVariable("id") Integer id){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Calificacion> lscalificaciones = serviceCalificacion.listarCalificacionesArrendatario(id);
			response.put(Constantes.AADATA_TXT_RESPONSE,lscalificaciones);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_CALIFICACION_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/registrarCalificacion")
	public ResponseEntity<Map<String, Object>> registrarCalificacion(@RequestBody CalificacionDTO dto){
		Map<String,Object> response = new HashMap<>();
		try {
			serviceCalificacion.registrarCalificacion(dto);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.MSG_REGISTRAR_CALIFICAICON_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
