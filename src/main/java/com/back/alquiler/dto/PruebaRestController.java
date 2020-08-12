package com.back.alquiler.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class PruebaRestController {
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Dato1 dato){
		Map<String,Object> response = new HashMap<>();
		System.out.println(dato.toString());
		response.put("isSuccesfull", true);
		response.put("message","Persona registrada correctamente");
		response.put("objeto",dato);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Dato2 dato){
		Map<String,Object> response = new HashMap<>();
		dato.setId_usuario(1);
		dato.setToken("Asadh6ju435S%dfgbrWSqAwEQdRaeR#");
		System.out.println(dato.toString());
		response.put("isSuccesfull", true);
		response.put("message","Usuario registrado correctamente");
		response.put("usuario",dato);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
}
