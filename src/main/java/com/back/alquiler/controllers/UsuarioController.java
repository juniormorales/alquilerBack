package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.service.UsuarioService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@GetMapping("/listarDesactivadas")
	public ResponseEntity<?> listarCuentasNoActivadas(){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Arrendero> lsArrendero = service.listarCuentasNoActivadas();
			response.put("aaData",lsArrendero);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		} catch (Exception e) {
			response.put("mensaje", Constantes.msgListarCuentasDesactivadasError);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/activarCuenta")
	public ResponseEntity<?> activarCuenta(@RequestBody Usuario usuario){
		Map<String,Object> response = new HashMap<>();
		try {
			service.activarCuenta(usuario);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgActicarCuentaOk);
			response.put("tipo",Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);	
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgActivarCuentaError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/desactivarCuenta")
	public ResponseEntity<?> desactivarCuenta(@RequestBody Usuario usuario){
		Map<String,Object> response = new HashMap<>();
		try {
			service.inhabilitarCuenta(usuario);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgInhabilitarCuentaOk);
			response.put("tipo",Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);	
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgInhabilitarCuentaError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/verificarInhabilitado")
	public ResponseEntity<?> verificarInhabilitado(@RequestBody Usuario usuario){
		Map<String,Object> response = new HashMap<>();
		try {
			Boolean estaInhabilitado = service.verificarInhabilitado(usuario);
			if(estaInhabilitado) {
				response.put("titulo", Constantes.tituloError);
				response.put("mensaje", Constantes.msgCuentaInhabilitada);
				response.put("tipo",Constantes.error);
			}
			response.put("obj",estaInhabilitado);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);	
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgBuscarUsuarioError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}
