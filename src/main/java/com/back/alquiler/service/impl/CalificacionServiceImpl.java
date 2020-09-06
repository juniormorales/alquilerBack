package com.back.alquiler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.back.alquiler.dto.CalificacionDTO;
import com.back.alquiler.models.Arrendatario;
import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Calificacion;
import com.back.alquiler.repo.CalificacionRepo;
import com.back.alquiler.service.CalificacionService;

@Service
public class CalificacionServiceImpl implements CalificacionService {

	@Autowired
	CalificacionRepo repoCalificacion;

	@Override
	public List<Calificacion> listarCalificacionesArrendatario(Integer id) {
		Arrendatario arrendatario = new Arrendatario();
		arrendatario.setIdArrendatario(id);
		return repoCalificacion.findByArrendatario(arrendatario);

	}

	@Override
	public Calificacion registrarCalificacion(CalificacionDTO dto) {
		Calificacion calificacion = new Calificacion();
		calificacion.setCalificacion(dto.getCalificacion());
		calificacion.setComentario(dto.getComentario());
		calificacion.setArrendero(dto.getArrendero());
		calificacion.setArrendatario(dto.getArrendatario());
		return repoCalificacion.save(calificacion);
	}

}
