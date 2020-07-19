package com.back.alquiler.service;

import com.back.alquiler.models.Contrato;
import com.back.alquiler.models.Inquilino;

public interface ContratoService extends ICRUD<Contrato> {
	
	Contrato habilitarContrato(Inquilino inquilino);
}
