package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Renta;
import com.back.alquiler.service.RentaService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/renta")
public class RentaController {
	
	@Autowired
	RentaService service_renta;
	
	@PostMapping("/listarPendientesPorInquilino")
	public ResponseEntity<?> listarPorInquilino(@RequestBody Inquilino inquilino){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Renta> lsRentas = service_renta.listarRentasPendientes(inquilino);
			response.put("aaData",lsRentas);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarRentasInquilinoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}
