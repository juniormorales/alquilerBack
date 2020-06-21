package com.back.alquiler.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.models.Modulo;
import com.back.alquiler.models.Perfil;
import com.back.alquiler.service.ModuloService;
import com.back.alquiler.utils.Constantes;


@RestController
@RequestMapping("/api/modulo")
public class ModuloController {

	@Autowired
	private ModuloService service;

	@PostMapping("/listarPorPerfil")
	public ResponseEntity<?> listar(@RequestBody Perfil perfil) throws Exception {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Modulo> res_modulo = service.listarModuloPorPerfil(perfil);
			response.put("aaData",res_modulo);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

		} catch (Exception e) {
			response.put("mensaje", Constantes.msgListarXPerfilModuloError);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
