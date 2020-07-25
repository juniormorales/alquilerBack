package com.back.alquiler.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.back.alquiler.models.Pago;

public interface PagoService extends ICRUD<Pago> {
		
	List<Pago> listarPagosPorConfirmar(Integer id);
	
	List<Pago> listarPagosTotalInquilino(Integer id);
	
	List<Pago> listarPagosArrendero(Integer id);
	
	Pago registrarImagenVoucher(MultipartFile archivo, Integer id) throws IOException;
	
	Resource verFotoVoucher(Integer id) throws IOException;
	
	Pago rechazarPago(Pago pago);
	
	byte[] generarBoleta(Integer idPago) throws Exception;
	
	
}
