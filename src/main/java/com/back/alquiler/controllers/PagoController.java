package com.back.alquiler.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.back.alquiler.models.Pago;
import com.back.alquiler.service.PagoService;
import com.back.alquiler.utils.Constantes;
import com.back.alquiler.utils.ImagenErrorResponse;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
	
	@Autowired
	PagoService servicePago;

	
	@PostMapping("/enviarPagoConfirmacion")
	public ResponseEntity<Map<String, Object>> registrarPago(@RequestBody Pago pago) {
		Map<String, Object> response = new HashMap<>();
		try {
			Integer id = servicePago.registrar(pago).getIdPago();
			response.put(Constantes.ID_TXT_RESPONSE,id);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_PAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/uploadImageVoucher")
	public ResponseEntity<Map<String, Object>> subirImagenVoucher(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			servicePago.registrarImagenVoucher(archivo,id);
			response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_REGISTRAR_PAGOCONFIRMAR_OK);
			response.put(Constantes.TIPO_TXT_RESPONSE, Constantes.SUCCESS);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			return ImagenErrorResponse.errorRegistro(e);
		}catch (IOException e) {
			return ImagenErrorResponse.errorLectura(e);
		}
	}
	

	@GetMapping("/listarPagosPorConfirmar/{id}")
	public ResponseEntity<Map<String, Object>> listarPagosPorConfirmar(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
				List<Pago> lsPagos = servicePago.listarPagosPorConfirmar(id);
				response.put(Constantes.AADATA_TXT_RESPONSE,lsPagos);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_PAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/verVoucher/{id}")
	public ResponseEntity<?> verFotoVoucher(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			Resource recurso = servicePago.verFotoVoucher(id);
			HttpHeaders cabecera = new HttpHeaders();
			cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
			return new ResponseEntity<>(recurso,cabecera,HttpStatus.OK);

		} catch (MalformedURLException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.ERROR_LECTURA_FOTO);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return ImagenErrorResponse.errorLectura(e);
		}
	}
	
	@PostMapping("/confirmarPago")
	public ResponseEntity<Map<String, Object>> confirmarPago(@RequestBody Pago pago) {
		Map<String, Object> response = new HashMap<>();
		try {
				servicePago.modificar(pago);
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_CONFIRMAR_PAGO_OK);
				response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTUALIZAR_PAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/rechazarPago")
	public ResponseEntity<Map<String, Object>> rechazarPago(@RequestBody Pago pago) {
		Map<String, Object> response = new HashMap<>();
		try {
				servicePago.rechazarPago(pago);
				response.put(Constantes.TITULO_TXT_RESPONSE, Constantes.TITULO_OK);
				response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_RECHAZAR_PAGO_OK);
				response.put(Constantes.TIPO_TXT_RESPONSE,Constantes.SUCCESS);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_ACTUALIZAR_PAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarPagosTotalInquilino/{id}")
	public ResponseEntity<Map<String, Object>> listarPagosTotalInquilino(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
				List<Pago> lsPagos = servicePago.listarPagosTotalInquilino(id);
				response.put(Constantes.AADATA_TXT_RESPONSE,lsPagos);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_PAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarPagosTotalArrendero/{id}")
	public ResponseEntity<Map<String, Object>> listarPagosTotalArrendero(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
				List<Pago> lsPagos = servicePago.listarPagosArrendero(id);
				response.put(Constantes.AADATA_TXT_RESPONSE,lsPagos);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE_TXT_RESPONSE, Constantes.MSG_LISTAR_PAGO_ERROR);
			response.put(Constantes.ERROR_TXT_RESPONSE, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/descargarBoleta/{id}")
	public byte[] descargarBoleta(@PathVariable("id") Integer id) throws Exception{
		try {
			return servicePago.generarBoleta(id);
		} catch (DataAccessException e) {
			throw new Exception(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
	}
	
}
