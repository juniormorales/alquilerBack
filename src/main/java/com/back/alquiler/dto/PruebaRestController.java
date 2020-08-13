package com.back.alquiler.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		Dato1 persona = new Dato1();
		persona.setApellidos("Morales Brenis");
		persona.setNombres("Junior Angel");
		persona.setEdad(23);
		persona.setEmail("angel_96vir@hotmail.com");
		persona.setDni("73088001");
		persona.setGenero("Masculino");
		persona.setId_persona(1);
		persona.setUsuario(dato);

		System.out.println(dato.toString());
		response.put("isSuccesfull", true);
		response.put("message","Usuario registrado correctamente");
		response.put("persona",persona);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/obtenerPersona/{id}")
	public ResponseEntity<?> obtenerPersona(@PathVariable("id") Integer id){
		System.out.println("ID del usuario: " + id);
		Dato1 persona = new Dato1();
		persona.setApellidos("Morales Brenis");
		persona.setNombres("Junior Angel");
		persona.setEdad(23);
		persona.setGenero("Masculino");
		persona.setId_persona(1);
		persona.setEmail("angel_96vir@hotmail.com");
		persona.setDni("73058001");
		Map<String,Object> response = new HashMap<>();
		response.put("isSuccesfull", true);
		response.put("message","Listado correctamente");
		response.put("objeto",persona);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
