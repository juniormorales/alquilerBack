package com.back.alquiler.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.back.alquiler.models.ImagenPropiedad;
import com.back.alquiler.models.Propiedad;
import com.back.alquiler.repo.ImagenPropiedadRepo;
import com.back.alquiler.service.ImagenPropiedadService;
import com.back.alquiler.utils.Constantes;

@Service
public class ImagenPropiedadServiceImpl implements ImagenPropiedadService {

	@Autowired
	ImagenPropiedadRepo repo_imagen_prop;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ImagenPropiedad registrar(ImagenPropiedad obj) {
		try {
			return repo_imagen_prop.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ImagenPropiedad modificar(ImagenPropiedad obj) {
		try {
			return repo_imagen_prop.save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ImagenPropiedad leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ImagenPropiedad> listar() {
		return repo_imagen_prop.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImagenPropiedad registrarImagen(Integer id, MultipartFile archivo) throws IOException {
		ImagenPropiedad imagen = new ImagenPropiedad();
		Propiedad p = new Propiedad();
		p.setIdPropiedad(id);
		imagen.setPropiedad(p);
		
		try {	
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get(Constantes.rutaImagenPropiedad).resolve(nombreArchivo).toAbsolutePath();
			Files.copy(archivo.getInputStream(), rutaArchivo);
			imagen.setNombreFoto(nombreArchivo);
			return repo_imagen_prop.save(imagen);
			
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean eliminarImagenes(List<Integer> ids) throws IOException {
		List<ImagenPropiedad> lsImagenes = new ArrayList<>();
		ids.forEach(id -> {
			lsImagenes.add(repo_imagen_prop.findByIdImagenPropiedad(id));
		});
		lsImagenes.forEach(imagen -> {
			String nombreFotoAnterior = imagen.getNombreFoto();
			if (nombreFotoAnterior != null) {
				Path rutaFotoAnterior = Paths.get(Constantes.rutaImagenPropiedad).resolve(nombreFotoAnterior)
						.toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
				repo_imagen_prop.deleteById(imagen.getIdImagenPropiedad());
			}
			;
		});
		return true;
	}

}
