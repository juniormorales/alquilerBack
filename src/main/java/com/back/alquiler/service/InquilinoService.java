package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Inquilino;

public interface InquilinoService  extends ICRUD<Inquilino>{
	
	List<Inquilino> listarPorArrenderoYContratoHecho(Integer id);
	
	Boolean darBajaInquilino(Inquilino inquilino);
}
