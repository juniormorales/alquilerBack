package com.back.alquiler.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.back.alquiler.models.ImagenPropiedad;
import com.back.alquiler.models.Propiedad;

public interface ImagenPropiedadService extends ICRUD<ImagenPropiedad> {
	
	public ImagenPropiedad registrarImagen(Integer id, MultipartFile archivo) throws IOException;
	
	public Boolean eliminarImagenes(List<Integer> ids) throws IOException;
	
	public Boolean eliminarTodasLasImagenes(Integer id) throws IOException;
}
