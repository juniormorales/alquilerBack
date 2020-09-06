package com.back.alquiler.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.alquiler.dto.JsonGeneral;
import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Pago;
import com.back.alquiler.models.SolicitudPropiedad;
import com.back.alquiler.repo.InquilinoRepo;
import com.back.alquiler.repo.PagoRepo;
import com.back.alquiler.repo.PropiedadRepo;
import com.back.alquiler.repo.SolicitudPropiedadRepo;
import com.back.alquiler.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	PagoRepo repoPago;

	@Autowired
	SolicitudPropiedadRepo repoSolicitud;

	@Autowired
	PropiedadRepo repoPropiedad;

	@Autowired
	InquilinoRepo repoInquilino;

	@Override
	public List<Map<String, Object>> retornarGananciaAños(Integer id, List<JsonGeneral> anios) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		List<Pago> pagos = repoPago.findByArrenderoAndEstado(arrendero, true);
		List<Map<String, Object>> response = new ArrayList<>();
		anios.forEach(json -> {
			List<Double> gananciapormes = new ArrayList<>();
			IntStream.range(0, 12).forEach(mes -> {
				gananciapormes
						.add(pagos.stream().filter(pago -> pago.getFechaConfirmado().getYear() + 1900 == json.getId())
								.filter(pago -> pago.getFechaConfirmado().getMonth() == mes)
								.mapToDouble(pago -> pago.getMonto()).sum());
			});

			Map<String, Object> ganancias = new HashMap<>();
			ganancias.put("anio", json.getItemName());
			ganancias.put("ganancias", gananciapormes);
			response.add(ganancias);
		});
		return response;
	}

	@Override
	public List<Map<String, Object>> retornaCantidadSolPropiedad(Integer id) {
		List<Map<String, Object>> resp = new ArrayList<>();
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		List<SolicitudPropiedad> listaSolicitudes = repoSolicitud.findByArrendero(arrendero);
		repoPropiedad.findByArrenderoAndConfirmado(arrendero, true).forEach(propiedad -> {
			Long cantidad = listaSolicitudes.stream()
					.filter(sol -> sol.getPropiedad().getIdPropiedad().equals(propiedad.getIdPropiedad()))
					.map(sol -> sol.getPropiedad()).count();
			Map<String, Object> response = new HashMap<>();
			response.put("propiedad", propiedad.getAlias());
			response.put("nrosol", cantidad);
			resp.add(response);
		});
		return resp;
	}

	@Override
	public Long cantidadInquilinosAlDia(Integer id) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		return repoInquilino.findByArrenderoAndEstadoPago(arrendero, true).stream().count();
	}

	@Override
	public Long cantidadInquilinosDeudores(Integer id) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		return repoInquilino.findByArrenderoAndEstadoPago(arrendero, false).stream().count();
	}

	@Override
	public Long cantidadPagosPorConfirmar(Integer id) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		return repoPago.findByArrenderoAndEstadoAndRechazado(arrendero, false, false).stream().count();

	}

	@Override
	public Long cantidadSolicitudesPendientes(Integer id) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		return repoSolicitud.findByArrenderoAndEstado(arrendero, 2).stream().count();
	}

}
