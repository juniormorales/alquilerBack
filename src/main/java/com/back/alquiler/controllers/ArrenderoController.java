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

import com.back.alquiler.dto.ArrenderoNuevoDTO;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.models.Arrendero.ArrenderoBuilder;
import com.back.alquiler.service.ArrenderoService;
import com.back.alquiler.service.UsuarioService;
import com.back.alquiler.utils.Constantes;


@RestController
@RequestMapping("/api/arrendero")
public class ArrenderoController {
	
	@Autowired
	ArrenderoService serviceArrendero;
	
	@Autowired
	UsuarioService serviceUsuario;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> registrar(@RequestBody ArrenderoNuevoDTO arrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Boolean yaexistedni = serviceUsuario.buscarSiExisteDNI(arrendero.usuario.getDni());
			if(yaexistedni) {
				response.put(Constantes.ESTADO_TXT_RESPONSE,false);
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_WARN);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_EXISTE_DNI);
				response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.WARNING);
			}else {
				Boolean yaexisteemail = serviceUsuario.buscarSiExisteEmail(arrendero.usuario.getEmail());
				if(yaexisteemail) {
					response.put(Constantes.ESTADO_TXT_RESPONSE,false);
					response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_WARN);
					response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_EXISTE_EMAIL);
					response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.WARNING);
				}else {
					Boolean yaexisteusername = serviceUsuario.buscarSiExisteUsername(arrendero.usuario.getUsername());
					if(yaexisteusername) {
						response.put(Constantes.ESTADO_TXT_RESPONSE,false);
						response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_WARN);
						response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_EXISTE_USERNAME);
						response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.WARNING);
					}else {
						arrendero.usuario.setPassword(passwordEncoder.encode(arrendero.usuario.getPassword()));
						Usuario user = serviceUsuario.registrar(arrendero.usuario);
						serviceArrendero.registrar((new ArrenderoBuilder(user))
								.direccion(arrendero.direccionActual)
								.nroPartida(arrendero.nroPartidaRegistral)
								.ubigeo(arrendero.departamento, arrendero.provincia, arrendero.distrito)
								.build());
						response.put(Constantes.ESTADO_TXT_RESPONSE,true);
						response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
						response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_USUARIO_OK + " " + Constantes.MSG_REGISTRAR_ARRENDERO_OK);
						response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.SUCCESS);
					}
				}	
			}
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_USUARIO_ERROR);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}
