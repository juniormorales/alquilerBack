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
	UsuarioService service_usuario;
	
	@GetMapping("/listarCuentas")
	public ResponseEntity<?> listarcuentas(){
		Map<String,Object> response = new HashMap<>();
		try {
			List<Usuario> lsUsu = service_usuario.listar();
			response.put("aaData", lsUsu);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje",Constantes.msgListarCuentasError);
			response.put("error",e.getMessage());
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
