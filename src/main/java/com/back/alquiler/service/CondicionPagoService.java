package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.CondicionPago;

public interface CondicionPagoService extends ICRUD<CondicionPago> {

	List<CondicionPago> listarPorArrendero(Integer id);
}
