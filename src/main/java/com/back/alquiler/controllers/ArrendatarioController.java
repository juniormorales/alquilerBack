package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.dto.ArrendatarioNuevoDTO;
import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.service.ArrendatarioService;
import com.back.alquiler.service.UsuarioService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/arrendatario")
public class ArrendatarioController {
	
	@Autowired
	ArrendatarioService serviceArrendatario;
	
	@Autowired
	UsuarioService serviceUsuario;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> registrar(@RequestBody ArrendatarioNuevoDTO arrendatario){
		Map<String,Object> response = new HashMap<>();
		try {
			Boolean yaexistedni = serviceUsuario.buscarSiExisteDNI(arrendatario.getUsuario().getDni());
			if(yaexistedni) {
				response.put(Constantes.ESTADO_TXT_RESPONSE,false);
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_WARN);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_EXISTE_DNI);
				response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.WARNING);
			}else {
				Boolean yaexisteemail = serviceUsuario.buscarSiExisteEmail(arrendatario.getUsuario().getEmail());
				if(yaexisteemail) {
					response.put(Constantes.ESTADO_TXT_RESPONSE,false);
					response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_WARN);
					response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_EXISTE_EMAIL);
					response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.WARNING);
				}else {
					Boolean yaexisteusername = serviceUsuario.buscarSiExisteUsername(arrendatario.getUsuario().getUsername());
					if(yaexisteusername) {
						response.put(Constantes.ESTADO_TXT_RESPONSE,false);
						response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_WARN);
						response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_EXISTE_USERNAME);
						response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.WARNING);
					}else {
						arrendatario.getUsuario().setPassword(passwordEncoder.encode(arrendatario.getUsuario().getPassword()));
						Usuario user = serviceUsuario.registrar(arrendatario.getUsuario());
						serviceArrendatario.registrar(new Arrendatario(arrendatario.getDireccionTemporal(),user));
						response.put(Constantes.ESTADO_TXT_RESPONSE,true);
						response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
						response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_USUARIO_OK);
						response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.SUCCESS);
					}
				}	
			}
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_USUARIO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}
