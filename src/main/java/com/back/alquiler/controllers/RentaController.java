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

import com.back.alquiler.dto.InquilinoDTO;
import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Renta;
import com.back.alquiler.service.RentaService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/renta")
public class RentaController {
	
	@Autowired
	RentaService serviceRenta;
	
	@PostMapping("/listarPendientesPorInquilino")
	public ResponseEntity<Map<String,Object>> listarPendientesPorInquilino(@RequestBody InquilinoDTO inquilino){
		Map<String,Object> response = new HashMap<>();
		Inquilino inqui = new Inquilino();
		inqui.setIdInquilino(inquilino.getIdInquilino());
		try {
			List<Renta> lsRentas = serviceRenta.listarRentasPendientes(inqui);
			response.put(Constantes.AADATA_TXT_RESPONSE,lsRentas);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_RENTA_INQUILINO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/listarCanceladosPorInquilino")
	public ResponseEntity<Map<String,Object>> listarCanceladosPorInquilino(@RequestBody InquilinoDTO inquilino){
		Map<String,Object> response = new HashMap<>();
		Inquilino inqui = new Inquilino();
		inqui.setIdInquilino(inquilino.getIdInquilino());
		try {
			List<Renta> lsRentas = serviceRenta.listarRentasCanceladas(inqui);
			response.put(Constantes.AADATA_TXT_RESPONSE,lsRentas);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_RENTA_INQUILINO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/listardeudasRentaPorInquilino")
	public ResponseEntity<Map<String,Object>> listarDeudasRentaPorInquilino(@RequestBody InquilinoDTO inquilino){
		Map<String,Object> response = new HashMap<>();
		Inquilino inqui = new Inquilino();
		inqui.setIdInquilino(inquilino.getIdInquilino());
		try {
			List<Renta> lsRentas = serviceRenta.listarDeudasRenta(inqui);
			response.put(Constantes.AADATA_TXT_RESPONSE,lsRentas);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_RENTA_INQUILINO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}
