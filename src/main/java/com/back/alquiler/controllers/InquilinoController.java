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
	InquilinoService serviceInquilino;
	
	@GetMapping("/listarSinContrato/{id}")
	public ResponseEntity<Map<String,Object>> listarPorArrenderoSinContrato(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			List<Inquilino> lsInq = serviceInquilino.listarPorArrenderoYContratoNoHecho(id);
			response.put(Constantes.AADATA_TXT_RESPONSE,lsInq);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_INQUILINO_PORARRENDERO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Map<String,Object>> listarPorArrendero(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			List<Inquilino> lsInq = serviceInquilino.listarPorArrenderoYContratoHecho(id);
			response.put(Constantes.AADATA_TXT_RESPONSE,lsInq);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_INQUILINO_PORARRENDERO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@GetMapping("/obtenerInquilino/{id}")
	public ResponseEntity<Map<String,Object>> obtenerInquilino(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			Inquilino resp = serviceInquilino.obtenerInquilinoActivo(id);
			response.put(Constantes.DEAFULT_OBJ_TXT_RESPONSE,resp);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_INQUILINO_PORARRENDERO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/darBaja")
	public ResponseEntity<Map<String,Object>> listarPorArrendero(@RequestBody Inquilino inquilino){
		Map<String,Object> response = new HashMap<>();
		
		try {
			Boolean elimino = serviceInquilino.darBajaInquilino(inquilino);
			response.put(Constantes.ESTADO_TXT_RESPONSE,elimino);
			if(elimino) {				
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_DARBAJA_INQUILINO_OK);
				response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.SUCCESS);
			}else {
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_WARN);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_DARBAJA_INQUILINO_AUNNO);
				response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.WARNING);
			}
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}  catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_DARBAJA_INQUILINO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}
