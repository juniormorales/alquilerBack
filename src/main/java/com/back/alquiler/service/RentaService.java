package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Renta;

public interface RentaService extends ICRUD<Renta>{
	
	List<Renta> listarRentasPendientes(Inquilino inquilino);
}
