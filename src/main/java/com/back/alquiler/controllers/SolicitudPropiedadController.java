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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.SolicitudPropiedad;
import com.back.alquiler.service.SolicitudPropiedadService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/sol-prop")
public class SolicitudPropiedadController {
	
	@Autowired
	SolicitudPropiedadService service_sol_prop;
	
	@GetMapping("/listarParaArrendero/{id}")
	ResponseEntity<?> listarPorArrendero(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			List<SolicitudPropiedad> lsSol = service_sol_prop.listarSolPendienteyAceptado(id);
			response.put("aaData",lsSol);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarSolError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping("/listarParaArrendatario/{id}")
	ResponseEntity<?> listarPorArrendatarioHistorial(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			List<SolicitudPropiedad> lsSol = service_sol_prop.listarSolArrendatario(id);
			response.put("aaData",lsSol);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarSolError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}
