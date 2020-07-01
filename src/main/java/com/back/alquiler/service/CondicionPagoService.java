package com.back.alquiler.service;

import com.back.alquiler.models.CondicionPago;

public interface CondicionPagoService extends ICRUD<CondicionPago> {

	Boolean buscarSiExisteCondicionPago(CondicionPago condicionPago);

}
