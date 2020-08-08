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
@RequestMapping("/usuario")
public class PruebaRestController {
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody Dato1 dato){
		Map<String,Object> response = new HashMap<>();
		System.out.println(dato.toString());
		response.put("error", false);
		response.put("id",1);
		response.put("message","Su registro ha sido completado");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
}
