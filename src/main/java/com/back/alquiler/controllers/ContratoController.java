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

import com.back.alquiler.models.Contrato;
import com.back.alquiler.models.Inquilino;
import com.back.alquiler.service.ContratoService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {
	
	@Autowired
	ContratoService service_contrato;
	
	@PostMapping("/habilitarContrato")
	public ResponseEntity<?> registrarContrato(@RequestBody Inquilino inquilino){
		Map<String,Object> response = new HashMap<>();
		try {
			Contrato contrato = service_contrato.habilitarContrato(inquilino);
			String ruta = service_contrato.crearContrato(contrato.getInquilino());
			contrato.setArchivoContrato(ruta);
			service_contrato.modificar(contrato);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgCrearContratoOk);
			response.put("tipo", Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgHabilitarContratoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			response.put("mensaje", Constantes.msgHabilitarContratoError);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/descargarContrato/{id}")
	public byte[] descargarContrato(@PathVariable("id") Integer id) throws Exception{
		try {
				byte[] file = service_contrato.obtenerContrato(id);
				
			return file;
		} catch (DataAccessException e) {
			throw new Exception(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
	}
	
	@GetMapping("listarContratosArrendero/{id}")
	public ResponseEntity<?> listarContratosPorArrendero(@PathVariable("id") Integer id){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Contrato> lsContrato = service_contrato.listarPorArrendero(id);
			response.put("aaData",lsContrato);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}catch(Exception e) {
			response.put("mensaje", Constantes.msgListarContratoError);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
}
