package com.back.alquiler.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EliminarResponse {
	
	private EliminarResponse() {
		
	}
	
	public static void eliminoOk(Map<String, Object> response) {
		response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
		response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ELIMINAR_OK);
		response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);
	}
	
	public static void eliminoError(Map<String, Object> response) {
		response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_ERROR);
		response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ELIMINAR_ERRROR);
		response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.ERROR);
	}
	
	public static ResponseEntity<Map<String,Object>> dataIntegrityError(DataIntegrityViolationException e){
		Map<String, Object> response = new HashMap<>();
		response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_CONTRATO_ERROR);
		response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
