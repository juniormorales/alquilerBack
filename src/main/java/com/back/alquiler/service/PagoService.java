package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Pago;

public interface PagoService extends ICRUD<Pago> {
	
	Pago confirmarPago(Pago pago);
	
	List<Pago> listarPagosPorConfirmar(Integer id);
}
