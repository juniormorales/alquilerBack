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
	SolicitudPropiedadService service_sol_prop;
	
	@Autowired
	InquilinoService service_inquilino;

	@PostMapping("/registrarSolicitud")
	ResponseEntity<?> registrarSolicitudPropiedad(@RequestBody SolicitudPropiedad sol) {
		Map<String, Object> response = new HashMap<>();

		try {
			service_sol_prop.registrar(sol);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgRegistrarSolPropOk);
			response.put("tipo", Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarSolPropError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarParaArrendero/{id}")
	ResponseEntity<?> listarPorArrendero(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			List<SolicitudPropiedad> lsSol = service_sol_prop.listarSolPendienteyAceptado(id);
			response.put("aaData", lsSol);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarSolError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarParaArrendatario/{id}")
	ResponseEntity<?> listarPorArrendatarioHistorial(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			List<SolicitudPropiedad> lsSol = service_sol_prop.listarSolArrendatario(id);
			response.put("aaData", lsSol);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarSolError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarSolAcep/{id}")
	ResponseEntity<?> listarSolAceptadas(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			List<SolicitudPropiedad> lsSol = service_sol_prop.listarSolAceptadas(id);
			response.put("aaData", lsSol);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarSolError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/modificar")
	ResponseEntity<?> listarPorArrendatarioHistorial(@RequestBody SolicitudPropiedad sol) {
		Map<String, Object> response = new HashMap<>();
		try {
			service_sol_prop.modificar(sol);
			if (sol.getEstado() == 4) {
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgCancelarSolOk);
				response.put("tipo", Constantes.success);
			} else {
				if (sol.getEstado() == 3) {
					service_inquilino.registrarInquilinoSinContrato(sol);
					response.put("titulo", Constantes.tituloOk);
					response.put("mensaje", Constantes.msgAceptarAprobacionOk);
					response.put("tipo", Constantes.success);
				} else {
					if (sol.getEstado() == 0) {
						response.put("titulo", Constantes.tituloOk);
						response.put("mensaje", Constantes.msgRechazarSolOk);
						response.put("tipo", Constantes.success);
					} else {
						response.put("titulo", Constantes.tituloOk);
						response.put("mensaje", Constantes.msgActualizarSolOk);
						response.put("tipo", Constantes.success);
					}
				}
			}

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgActualizarSolError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscarSolicitudExistente/{idPropiedad}/{idArrendatario}")
	public ResponseEntity<?> buscarSolExistente(@PathVariable("idPropiedad") Integer idPropiedad,@PathVariable("idArrendatario") Integer idArrendatario){
		Map<String,Object> response = new HashMap<>();
		try {
			Boolean resp = service_sol_prop.buscarSolExistente(idPropiedad,idArrendatario);
			response.put("encontro",resp);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgBuscarSolExistenteError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
