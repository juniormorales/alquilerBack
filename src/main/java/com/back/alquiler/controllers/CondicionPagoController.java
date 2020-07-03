package com.back.alquiler.controllers;

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

import com.back.alquiler.models.CondicionPago;
import com.back.alquiler.service.CondicionPagoService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/condicion-pago")
public class CondicionPagoController {

	@Autowired
	CondicionPagoService service_condicion_pago;

	@PostMapping("/registrar")
	public ResponseEntity<?> registrarCondicionPago(@RequestBody CondicionPago condicionPago) {
		Map<String, Object> response = new HashMap<>();
		try {
			service_condicion_pago.registrar(condicionPago);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgRegistrarCondicionPagoOk);
			response.put("tipo", Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarCondicionPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/modificar")
	public ResponseEntity<?> modificarCondicionPago(@RequestBody CondicionPago condicionPago) {
		Map<String, Object> response = new HashMap<>();
		try {
			service_condicion_pago.modificar(condicionPago);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgActualizarCondicionPagoOk);
			response.put("tipo", Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgActualizarCondicionPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<?> listarCondicionPorArrendero(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		try {
			List<CondicionPago> lsCondicion = service_condicion_pago.listarPorArrendero(id);
			response.put("aaData",lsCondicion);
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje",Constantes.msgListarCondicionPagoError);
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCondicionPago(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Boolean elimino = service_condicion_pago.eliminar(id);
			if(elimino) {
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgEliminarOk);
				response.put("tipo", Constantes.success);
			}else {
				response.put("titulo", Constantes.tituloError);
				response.put("mensaje", Constantes.msgEliminarError);
				response.put("tipo", Constantes.error);
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (DataIntegrityViolationException e) {
			response.put("mensaje", Constantes.msgEliminarErrorGrave);
			response.put("error", Constantes.msgEliminarErrorGraveDesc);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
