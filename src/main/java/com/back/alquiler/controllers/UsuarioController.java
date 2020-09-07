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

import com.back.alquiler.dto.UsuarioDTO;
import com.back.alquiler.models.Arrendatario;
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
	public ResponseEntity<Map<String, Object>> listarCuentasNoActivadas(){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Arrendero> lsArrendero = service.listarCuentasNoActivadas();
			response.put(Constantes.AADATA_TXT_RESPONSE,lsArrendero);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_CUENTASDESACT_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/activarCuenta")
	public ResponseEntity<Map<String, Object>> activarCuenta(@RequestBody UsuarioDTO usuario){
		Map<String,Object> response = new HashMap<>();
		try {
			Usuario user = new Usuario();
			user.setIdUsuario(usuario.getIdUsuario());
			service.activarCuenta(user);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTIVAR_CUENTA_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);	
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTIVAR_CUENTA_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/desactivarCuenta")
	public ResponseEntity<Map<String, Object>> desactivarCuenta(@RequestBody UsuarioDTO usuario){
		Map<String,Object> response = new HashMap<>();
		try {
			Usuario user = new Usuario();
			user.setIdUsuario(usuario.getIdUsuario());
			service.inhabilitarCuenta(user);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_INHABILITAR_CUENTA_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);	
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_INHABILITAR_CUENTA_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/verificarInhabilitado")
	public ResponseEntity<Map<String, Object>> verificarInhabilitado(@RequestBody UsuarioDTO usuario){
		Map<String,Object> response = new HashMap<>();
		try {
			Usuario user = new Usuario();
			user.setUsername(usuario.getUsername());
			Boolean estaInhabilitado = service.verificarInhabilitado(user);
			if(estaInhabilitado) {
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_ERROR);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_CUENTA_INHABILITADA);
				response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.ERROR);
			}
			response.put(Constantes.OBJ_TXT_RESPONSE,estaInhabilitado);
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);	
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_BUSCAR_USUARIO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/retornarArrendero")
	public ResponseEntity<Map<String, Object>> retornaArrendero(@RequestBody UsuarioDTO usuario){
		Map<String,Object> response = new HashMap<>();
		try {
			Usuario user = new Usuario();
			user.setIdUsuario(usuario.getIdUsuario());
			Arrendero arrendero = service.retornarArrendero(user);
			arrendero.setUsuario(null);
			response.put(Constantes.OBJ_TXT_RESPONSE,arrendero);
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);	
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_BUSCAR_USUARIO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@PostMapping("/retornarArrendatario")
	public ResponseEntity<Map<String, Object>> retornaArrendatario(@RequestBody UsuarioDTO usuario){
		Map<String,Object> response = new HashMap<>();
		try {
			Usuario user = new Usuario();
			user.setIdUsuario(usuario.getIdUsuario());
			Arrendatario arrendatario = service.retornarArrendatario(user);
			arrendatario.setUsuario(null);
			response.put(Constantes.OBJ_TXT_RESPONSE,arrendatario);
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);	
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_BUSCAR_USUARIO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
}
