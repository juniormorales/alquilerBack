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
	ContratoService serviceContrato;

	@PostMapping("/habilitarContrato")
	public ResponseEntity<Map<String, Object>> registrarContrato(@RequestBody Inquilino inquilino) {
		Map<String, Object> response = new HashMap<>();
		try {
			Contrato contrato = serviceContrato.habilitarContrato(inquilino);
			String ruta = serviceContrato.crearContrato(contrato.getInquilino());
			contrato.setArchivoContrato(ruta);
			serviceContrato.modificar(contrato);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_CREAR_CONTRATO_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.MSG_HABILITAR_CONTRATO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,
					e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.MSG_HABILITAR_CONTRATO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/descargarContrato/{id}")
	public byte[] descargarContrato(@PathVariable("id") Integer id) throws Exception {
		return serviceContrato.obtenerContrato(id);
	}

	@GetMapping("listarContratosArrendero/{id}")
	public ResponseEntity<Map<String, Object>> listarContratosPorArrendero(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Contrato> lsContrato = serviceContrato.listarPorArrendero(id);
			response.put(Constantes.AADATA_TXT_RESPONSE, lsContrato);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_CONTRATO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
