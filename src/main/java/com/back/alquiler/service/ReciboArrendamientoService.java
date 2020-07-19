package com.back.alquiler.service;

import com.back.alquiler.models.Pago;
import com.back.alquiler.models.ReciboArrendamiento;

public interface ReciboArrendamientoService extends ICRUD<ReciboArrendamiento> {
	
	Boolean crearReciboArrendamiento(Pago pago);
}
