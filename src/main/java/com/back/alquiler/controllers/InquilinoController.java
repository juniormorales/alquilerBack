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

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.service.InquilinoService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/inquilino")
public class InquilinoController {
	
	@Autowired
	InquilinoService service_inquilino;
	
	@GetMapping("/listarSinContrato/{id}")
	ResponseEntity<?> listarPorArrenderoSinContrato(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			List<Inquilino> lsInq = service_inquilino.listarPorArrenderoYContratoNoHecho(id);
			response.put("aaData",lsInq);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarInquilinosPorArrenderoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping("/listar/{id}")
	ResponseEntity<?> listarPorArrendero(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			List<Inquilino> lsInq = service_inquilino.listarPorArrenderoYContratoHecho(id);
			response.put("aaData",lsInq);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarInquilinosPorArrenderoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping("/obtenerInquilino/{id}")
	ResponseEntity<?> obtenerInquilino(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			Inquilino resp = service_inquilino.obtenerInquilinoActivo(id);
			response.put("defaultObj",resp);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarInquilinosPorArrenderoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/darBaja")
	ResponseEntity<?> listarPorArrendero(@RequestBody Inquilino inquilino){
		Map<String,Object> response = new HashMap<>();
		
		try {
			Boolean elimino = service_inquilino.darBajaInquilino(inquilino);
			response.put("estado",elimino);
			if(elimino) {				
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgDarBajaInquilinoOk);
				response.put("tipo",Constantes.success);
			}else {
				response.put("titulo", Constantes.tituloWarn);
				response.put("mensaje", Constantes.msgDarBajaInquilinoAunNo);
				response.put("tipo",Constantes.warning);
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgDarBajaInquilinoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}
