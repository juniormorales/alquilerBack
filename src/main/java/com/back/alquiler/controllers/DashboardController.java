package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.dto.JsonGeneral;
import com.back.alquiler.service.DashboardService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@Autowired
	DashboardService serviceDashboard;
	
	@PostMapping("/ganancias/{idarrendero}")
	public ResponseEntity<Map<String, Object>> listarGananciasAlquiler(@PathVariable("idarrendero") Integer idarrendero, @RequestBody List<JsonGeneral> anios){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Map<String, Object>> ganancias = serviceDashboard.retornarGananciaAños(idarrendero, anios);
			response.put(Constantes.DATA_TXT_RESPONSE,ganancias);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_GANANCIA_ANUAL_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/solicitudPorPropiedad/{idarrendero}")
	public ResponseEntity<Map<String,Object>> listarCantidadSolPorPropiedad(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Map<String, Object>> cantidadsol = serviceDashboard.retornaCantidadSolPropiedad(idarrendero);
			response.put(Constantes.DATA_TXT_RESPONSE,cantidadsol);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_CANTIDAD_SOL_PROP_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantidadInquilinosAlDia/{idarrendero}")
	public ResponseEntity<Map<String,Object>> listarCantidadInquilinosAlDia(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Long cantidad = serviceDashboard.cantidadInquilinosAlDia(idarrendero);
			response.put(Constantes.DATA_TXT_RESPONSE,cantidad);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_CANTIDAD_INQUILINO_ALDIA_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantidadInquilinosDeudores/{idarrendero}")
	public ResponseEntity<?> listarCantidadInquilinosDeudores(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Long cantidad = serviceDashboard.cantidadInquilinosDeudores(idarrendero);
			response.put(Constantes.DATA_TXT_RESPONSE,cantidad);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_CANTIDAD_INQUILINO_DEUDOR_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantidadPagosPorconfirmar/{idarrendero}")
	public ResponseEntity<Map<String,Object>> listarCantidadPagosPorConfirmar(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Long cantidad = serviceDashboard.cantidadPagosPorConfirmar(idarrendero);
			response.put(Constantes.DATA_TXT_RESPONSE,cantidad);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_CANTIDAD_PAGO_CONFIRMAR_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantidadSolicitudesPendientes/{idarrendero}")
	public ResponseEntity<Map<String,Object>> listarCantidadSolicitudesPendientes(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Long cantidad = serviceDashboard.cantidadSolicitudesPendientes(idarrendero);
			response.put(Constantes.DATA_TXT_RESPONSE,cantidad);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_CANT_SOL_PENDIENTE_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
