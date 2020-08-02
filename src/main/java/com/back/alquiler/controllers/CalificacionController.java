package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Calificacion;
import com.back.alquiler.service.CalificacionService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/calificacion")
public class CalificacionController {
	
	@Autowired
	CalificacionService service_calificacion;
	
	@GetMapping("/listarCalificacionPorArrendatario/{id}")
	public ResponseEntity<?> listarCalificacionesArrendatario(@PathVariable("id") Integer id){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Calificacion> lscalificaciones = service_calificacion.listarCalificacionesArrendatario(id);
			response.put("aaData",lscalificaciones);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje", Constantes.msgListarCalificacionesError);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
