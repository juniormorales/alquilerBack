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
import com.back.alquiler.utils.EliminarResponse;

@RestController
@RequestMapping("/api/condicion-pago")
public class CondicionPagoController {

	@Autowired
	CondicionPagoService serviceCondicionPago;

	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> registrarCondicionPago(@RequestBody CondicionPago condicionPago) {
		Map<String, Object> response = new HashMap<>();
		try {
			serviceCondicionPago.registrar(condicionPago);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_CONDICPAGO_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_CONDICPAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/modificar")
	public ResponseEntity<Map<String, Object>> modificarCondicionPago(@RequestBody CondicionPago condicionPago) {
		Map<String, Object> response = new HashMap<>();
		try {
			serviceCondicionPago.modificar(condicionPago);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTUALIZAR_CONDICPAGO_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTUALIZAR_CONDICPAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Map<String,Object>> listarCondicionPorArrendero(@PathVariable Integer id){
		Map<String,Object> response = new HashMap<>();
		try {
			List<CondicionPago> lsCondicion = serviceCondicionPago.listarPorArrendero(id);
			response.put(Constantes.AADATA_TXT_RESPONSE,lsCondicion);	
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_LISTAR_CONDICPAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Object>> eliminarCondicionPago(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			boolean elimino = serviceCondicionPago.eliminar(id);
			if(Boolean.TRUE.equals(elimino)) {
				EliminarResponse.eliminoOk(response);
			}else {
				EliminarResponse.eliminoError(response);
			}
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (DataIntegrityViolationException e) {
			return EliminarResponse.dataIntegrityError(e);
		}
	}

}
