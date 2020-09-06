package com.back.alquiler.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	ImagenPropiedadRepo repoImagenProp;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ImagenPropiedad registrar(ImagenPropiedad obj) {
		return repoImagenProp.save(obj);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ImagenPropiedad modificar(ImagenPropiedad obj) {
		return repoImagenProp.save(obj);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ImagenPropiedad> listar() {
		return repoImagenProp.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean eliminar(Integer id) {
		if (repoImagenProp.existsById(id)) {
			repoImagenProp.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ImagenPropiedad registrarImagen(Integer id, MultipartFile archivo) throws IOException {
		ImagenPropiedad imagen = new ImagenPropiedad();
		Propiedad p = new Propiedad();
		p.setIdPropiedad(id);
		imagen.setPropiedad(p);

		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = Paths.get(Constantes.RUTA_IMAGEN_PROPIEDAD).resolve(nombreArchivo).toAbsolutePath();
		Files.copy(archivo.getInputStream(), rutaArchivo);
		imagen.setNombreFoto(nombreArchivo);
		return repoImagenProp.save(imagen);

	}

	@Override
	public Boolean eliminarImagenes(List<Integer> ids) throws IOException {
		List<ImagenPropiedad> lsImagenes = new ArrayList<>();
		ids.forEach(id -> lsImagenes.add(repoImagenProp.findByIdImagenPropiedad(id)));
		eliminarImagenesAux(lsImagenes);
		return true;
	}

	@Override
	public Boolean eliminarTodasLasImagenes(Integer id) throws IOException {
		Propiedad propiedad = new Propiedad();
		propiedad.setIdPropiedad(id);
		List<ImagenPropiedad> lsImagenes = repoImagenProp.findByPropiedad(propiedad);
		if (!lsImagenes.isEmpty()) {
			eliminarImagenesAux(lsImagenes);
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> retornarImagenes(Integer id) throws IOException {
		List<Map<String, Object>> lsMaps = new ArrayList<>();
		String directorio = System.getProperty("user.dir");
		String separador = System.getProperty("file.separator");
		Propiedad propiedad = new Propiedad();
		propiedad.setIdPropiedad(id);
		List<ImagenPropiedad> lsImagen = repoImagenProp.findByPropiedad(propiedad);
		lsImagen.forEach(imagen -> {
			String ruta = directorio + separador + Constantes.RUTA_IMAGEN_PROPIEDAD + separador
					+ imagen.getNombreFoto();
			File archivo = new File(ruta);
			imagen.setPropiedad(null);
			if (archivo.exists()) {
				Map<String, Object> dataImagen = new HashMap<>();
				try {
					dataImagen.put(Constantes.FOTO_TXT_RESPONSE, Files.readAllBytes(archivo.toPath()));
				} catch (IOException e) {
					dataImagen.put(Constantes.FOTO_TXT_RESPONSE, null);
				}
				dataImagen.put(Constantes.OBJ_TXT_RESPONSE, imagen);
				lsMaps.add(dataImagen);
			}
		});
		return lsMaps;
	}
	
	private void eliminarImagenesAux(List<ImagenPropiedad> lsImagenes) {
		lsImagenes.forEach(imagen -> {
			String nombreFotoAnterior = imagen.getNombreFoto();
			if (nombreFotoAnterior != null) {
				Path rutaFotoAnterior = Paths.get(Constantes.RUTA_IMAGEN_PROPIEDAD).resolve(nombreFotoAnterior)
						.toAbsolutePath();
					try {
						Files.delete(rutaFotoAnterior);
						repoImagenProp.deleteById(imagen.getIdImagenPropiedad());
					} catch (IOException e) {
						imagen.setNombreFoto("");
					}
			}
		});
	}

}
