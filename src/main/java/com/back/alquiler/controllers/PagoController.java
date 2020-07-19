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
import com.back.alquiler.service.ReciboArrendamientoService;
import com.back.alquiler.utils.Constantes;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
	
	@Autowired
	PagoService service_pago;
	
	@Autowired
	ReciboArrendamientoService service_recibo_arrendamiento;
	
	@PostMapping("/enviarPagoConfirmacion")
	public ResponseEntity<?> registrarPago(@RequestBody Pago pago) {
		Map<String, Object> response = new HashMap<>();
		try {
			Integer id = service_pago.registrar(pago).getIdPago();
			response.put("id",id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgRegistrarPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/uploadImageVoucher")
	public ResponseEntity<?> subirImagenVoucher(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			service_pago.registrarImagenVoucher(archivo,id);
			response.put("titulo", Constantes.tituloOk);
			response.put("mensaje", Constantes.msgRegistrarPagoParaConfirmarOk);
			response.put("tipo", Constantes.success);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.errorRegistroFoto);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IOException e) {
			response.put("mensaje", Constantes.errorLecturaFoto);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/listarPagosPorConfirmar/{id}")
	public ResponseEntity<?> listarPagosPorConfirmar(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
				List<Pago> lsPagos = service_pago.listarPagosPorConfirmar(id);
				response.put("aaData",lsPagos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/verVoucher/{id}")
	public ResponseEntity<?> verFotoVoucher(@PathVariable Integer id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			Resource recurso = service_pago.verFotoVoucher(id);
			HttpHeaders cabecera = new HttpHeaders();
			cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
			return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);

		} catch (MalformedURLException e) {
			response.put("mensaje", Constantes.errorLecturaFoto);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}catch (RuntimeException e) {
			response.put("mensaje", Constantes.errorLecturaFoto);
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/confirmarPago")
	public ResponseEntity<?> confirmarPago(@RequestBody Pago pago) {
		Map<String, Object> response = new HashMap<>();
		try {
				Pago resp = service_pago.modificar(pago);
				service_recibo_arrendamiento.crearReciboArrendamiento(resp);
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgConfirmarPagoOk);
				response.put("tipo",Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgActualizarPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/rechazarPago")
	public ResponseEntity<?> rechazarPago(@RequestBody Pago pago) {
		Map<String, Object> response = new HashMap<>();
		try {
				service_pago.rechazarPago(pago);
				response.put("titulo", Constantes.tituloOk);
				response.put("mensaje", Constantes.msgRechazarPagoOk);
				response.put("tipo",Constantes.success);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgActualizarPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarPagosTotalInquilino/{id}")
	public ResponseEntity<?> listarPagosTotalInquilino(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
				List<Pago> lsPagos = service_pago.listarPagosTotalInquilino(id);
				response.put("aaData",lsPagos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarPagosTotalArrendero/{id}")
	public ResponseEntity<?> listarPagosTotalArrendero(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
				List<Pago> lsPagos = service_pago.listarPagosArrendero(id);
				response.put("aaData",lsPagos);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", Constantes.msgListarPagoError);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
