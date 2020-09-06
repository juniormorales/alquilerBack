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

import com.back.alquiler.models.SolicitudPropiedad;
import com.back.alquiler.service.InquilinoService;
import com.back.alquiler.service.SolicitudPropiedadService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/sol-prop")
public class SolicitudPropiedadController {

	@Autowired
	SolicitudPropiedadService serviceSolProp;
	
	@Autowired
	InquilinoService serviceInquilino;

	@PostMapping("/registrarSolicitud")
	public ResponseEntity<Map<String, Object>> registrarSolicitudPropiedad(@RequestBody SolicitudPropiedad sol) {
		Map<String, Object> response = new HashMap<>();

		try {
			serviceSolProp.registrar(sol);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_SOLICITUD_PROP_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_SOLICITUD_PROP_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarParaArrendero/{id}")
	public ResponseEntity<Map<String, Object>> listarPorArrendero(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			List<SolicitudPropiedad> lsSol = serviceSolProp.listarSolPendienteyAceptado(id);
			response.put(Constantes.AADATA_TXT_RESPONSE, lsSol);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_SOLICITUD_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarParaArrendatario/{id}")
	public ResponseEntity<Map<String, Object>> listarPorArrendatarioHistorial(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			List<SolicitudPropiedad> lsSol = serviceSolProp.listarSolArrendatario(id);
			response.put(Constantes.AADATA_TXT_RESPONSE, lsSol);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_SOLICITUD_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarSolAcep/{id}")
	public ResponseEntity<Map<String, Object>> listarSolAceptadas(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			List<SolicitudPropiedad> lsSol = serviceSolProp.listarSolAceptadas(id);
			response.put(Constantes.AADATA_TXT_RESPONSE, lsSol);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_SOLICITUD_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/modificar")
	public ResponseEntity<Map<String, Object>> listarPorArrendatarioHistorial(@RequestBody SolicitudPropiedad sol) {
		Map<String, Object> response = new HashMap<>();
		try {
			serviceSolProp.modificar(sol);
			if (sol.getEstado() == 4) {
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_CANCELAR_SOLICITUD_OK);
				response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
			} else {
				if (sol.getEstado() == 3) {
					serviceInquilino.registrarInquilinoSinContrato(sol);
					response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
					response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACEPTAR_APROBACION_OK);
					response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
				} else {
					if (sol.getEstado() == 0) {
						response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
						response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_RECHAZAR_SOLICITUD_OK);
						response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
					} else {
						response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
						response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTUALIZAR_SOLICITUD_OK);
						response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
					}
				}
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTUALIZAR_SOLICITUD_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscarSolicitudExistente/{idPropiedad}/{idArrendatario}")
	public ResponseEntity<Map<String, Object>> buscarSolExistente(@PathVariable("idPropiedad") Integer idPropiedad,@PathVariable("idArrendatario") Integer idArrendatario){
		Map<String,Object> response = new HashMap<>();
		try {
			Boolean resp = serviceSolProp.buscarSolExistente(idPropiedad,idArrendatario);
			response.put(Constantes.ENCONTRO_TXT_RESPONSE,resp);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_BUSCAR_SOLICITUD_EXISTENTE);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
