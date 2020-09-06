package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Usuario;
import com.back.alquiler.service.UsuarioService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/admin")
public class AdministracionController {
	
	@Autowired
	UsuarioService serviceUsuario;
	
	@GetMapping("/listarCuentas")
	public ResponseEntity<Map<String, Object>> listarcuentas(){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Usuario> lsUsu = serviceUsuario.listar();
			response.put(Constantes.AADATA_TXT_RESPONSE, lsUsu);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE,Constantes.MSG_LISTAR_CUENTA_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE,e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
