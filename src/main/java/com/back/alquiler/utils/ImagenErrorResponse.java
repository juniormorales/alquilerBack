package com.back.alquiler.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ImagenErrorResponse {
	
	private ImagenErrorResponse() {
		
	}
	
	public static ResponseEntity<Map<String, Object>> errorRegistro(DataAccessException e){
		Map<String,Object> response = new HashMap<>();
		response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.ERROR_REGISTRO_FOTO);
		response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public static ResponseEntity<Map<String, Object>> errorLectura(IOException e){
		Map<String,Object> response = new HashMap<>();
		response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.ERROR_LECTURA_FOTO);
		response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
