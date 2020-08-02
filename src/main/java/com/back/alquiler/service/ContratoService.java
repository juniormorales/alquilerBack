package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Contrato;
import com.back.alquiler.models.Inquilino;

public interface ContratoService extends ICRUD<Contrato> {
	
	Contrato habilitarContrato(Inquilino inquilino);
	
	String crearContrato(Inquilino inquilino) throws Exception;
	
	byte[] obtenerContrato(Integer id) throws Exception;
	
	List<Contrato> listarPorArrendero(Integer id);
} 
