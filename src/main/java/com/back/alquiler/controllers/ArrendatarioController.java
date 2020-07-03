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

import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Usuario;
import com.back.alquiler.service.ArrendatarioService;
import com.back.alquiler.service.UsuarioService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/arrendatario")
public class ArrendatarioController {
	
	@Autowired
	ArrendatarioService service_arrendatario;
	
	@Autowired
	UsuarioService service_usuario;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Arrendatario arrendatario){
		Map<String,Object> response = new HashMap<>();
		try {
			Boolean yaexistedni = service_usuario.buscarSiExisteDNI(arrendatario.getUsuario().getDni());
			if(yaexistedni) {
				response.put("estado",false);
				response.put("titulo", Constantes.tituloWarn);
				response.put("mensaje", Constantes.msgYaExisteDNI);
				response.put("tipo",Constantes.warning);
			}else {
				Boolean yaexisteemail = service_usuario.buscarSiExisteEmail(arrendatario.getUsuario().getEmail());
				if(yaexisteemail) {
					response.put("estado",false);
					response.put("titulo", Constantes.tituloWarn);
					response.put("mensaje", Constantes.msgYaExisteEmail);
					response.put("tipo",Constantes.warning);
				}else {
					Boolean yaexisteusername = service_usuario.buscarSiExisteUsername(arrendatario.getUsuario().getUsername());
					if(yaexisteusername) {
						response.put("estado",false);
						response.put("titulo", Constantes.tituloWarn);
						response.put("mensaje", Constantes.msgYaExisteUsername);
						response.put("tipo",Constantes.warning);
					}else {
						arrendatario.getUsuario().setPassword(passwordEncoder.encode(arrendatario.getUsuario().getPassword()));
						Usuario user = service_usuario.registrar(arrendatario.getUsuario());
						arrendatario.setUsuario(user);
						Arrendatario resp = service_arrendatario.registrar(arrendatario);
						response.put("estado",true);
						response.put("titulo", Constantes.tituloOk);
						response.put("mensaje", Constantes.msgRegistrarUsuarioOk);
						response.put("tipo",Constantes.success);
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
