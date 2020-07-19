package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.SolicitudPropiedad;

public interface InquilinoService  extends ICRUD<Inquilino>{
	
	List<Inquilino> listarPorArrenderoYContratoHecho(Integer id);
	
	List<Inquilino> listarPorArrenderoYContratoNoHecho(Integer id);
	
	Boolean darBajaInquilino(Inquilino inquilino);
	
	Inquilino registrarInquilinoSinContrato(SolicitudPropiedad sol);
	
	Inquilino obtenerInquilinoActivo(Integer id);
}
