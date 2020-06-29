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

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Perfil;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.service.ArrenderoService;
import com.back.alquiler.service.UsuarioService;
import com.back.alquiler.utils.Constantes;


@RestController
@RequestMapping("/api/arrendero")
public class ArrenderoController {
	
	@Autowired
	ArrenderoService service_arrendero;
	
	@Autowired
	UsuarioService service_usuario;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Arrendero arrendero){
		Map<String,Object> response = new HashMap<>();
		try {
			Boolean yaexistedni = service_usuario.buscarSiExisteDNI(arrendero.getUsuario().getDni());
			if(yaexistedni) {
				response.put("titulo", Constantes.tituloWarn);
				response.put("mensaje", Constantes.msgYaExisteDNI);
				response.put("tipo",Constantes.warning);
			}else {
				Boolean yaexisteemail = service_usuario.buscarSiExisteEmail(arrendero.getUsuario().getEmail());
				if(yaexisteemail) {
					response.put("titulo", Constantes.tituloWarn);
					response.put("mensaje", Constantes.msgYaExisteEmail);
					response.put("tipo",Constantes.warning);
				}else {
					Boolean yaexisteusername = service_usuario.buscarSiExisteUsername(arrendero.getUsuario().getUsername());
					if(yaexisteusername) {
						response.put("titulo", Constantes.tituloWarn);
						response.put("mensaje", Constantes.msgYaExisteUsername);
						response.put("tipo",Constantes.warning);
					}else {
						arrendero.getUsuario().setPassword(passwordEncoder.encode(arrendero.getUsuario().getPassword()));
						Usuario user = service_usuario.registrar(arrendero.getUsuario());
						arrendero.setUsuario(user);
						Arrendero resp = service_arrendero.registrar(arrendero);
						response.put("titulo", Constantes.tituloOk);
						response.put("mensaje", Constantes.msgRegistrarUsuarioOk);
						response.put("tipo",Constantes.success);
						response.put("defaultObj", resp);
					}
				}	
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarUsuarioError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
}
