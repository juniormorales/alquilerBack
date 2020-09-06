package com.back.alquiler.service;

import java.util.List;

import com.back.alquiler.models.Modulo;
import com.back.alquiler.models.Perfil;

public interface ModuloService {

	List<Modulo> listarModuloPorPerfil(Perfil perfil);

}
