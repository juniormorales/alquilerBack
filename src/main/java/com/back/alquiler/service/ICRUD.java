package com.back.alquiler.service;

import java.util.List;

public interface ICRUD<T> {

	T registrar(T obj);
	T modificar(T obj);
	T leer(Integer id);
	List<T> listar();
	Boolean eliminar(Integer id);
}
