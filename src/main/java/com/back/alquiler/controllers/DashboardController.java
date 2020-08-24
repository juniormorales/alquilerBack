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
	DashboardService service;
	
	@PostMapping("/ganancias/{idarrendero}")
	public ResponseEntity<?> listarGananciasAlquiler(@PathVariable("idarrendero") Integer idarrendero, @RequestBody List<JsonGeneral> anios){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Map<String, Object>> ganancias = service.retornarGananciaAños(idarrendero, anios);
			response.put("data",ganancias);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje",Constantes.msgMostrarGananciasAnualesDashError);
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/solicitudPorPropiedad/{idarrendero}")
	public ResponseEntity<?> listarCantidadSolPorPropiedad(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Map<String, Object>> cantidadsol = service.retornaCantidadSolPropiedad(idarrendero);
			response.put("data",cantidadsol);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje",Constantes.msgListarCantidadSolicitudesPorPropiedadError);
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantidadInquilinosAlDia/{idarrendero}")
	public ResponseEntity<?> listarCantidadInquilinosAlDia(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Long cantidad = service.cantidadInquilinosAlDia(idarrendero);
			response.put("data",cantidad);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje",Constantes.msgCantidadInquilinosAlDiaError);
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantidadInquilinosDeudores/{idarrendero}")
	public ResponseEntity<?> listarCantidadInquilinosDeudores(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Long cantidad = service.cantidadInquilinosDeudores(idarrendero);
			response.put("data",cantidad);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje",Constantes.msgCantidadInquilinosDeudoresError);
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantidadPagosPorconfirmar/{idarrendero}")
	public ResponseEntity<?> listarCantidadPagosPorConfirmar(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Long cantidad = service.cantidadPagosPorConfirmar(idarrendero);
			response.put("data",cantidad);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje",Constantes.msgCantidadPagosPorConfirmarError);
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cantidadSolicitudesPendientes/{idarrendero}")
	public ResponseEntity<?> listarCantidadSolicitudesPendientes(@PathVariable("idarrendero") Integer idarrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Long cantidad = service.cantidadSolicitudesPendientes(idarrendero);
			response.put("data",cantidad);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje",Constantes.msgCantidadSolicitudesPendientesError);
			response.put("error",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
