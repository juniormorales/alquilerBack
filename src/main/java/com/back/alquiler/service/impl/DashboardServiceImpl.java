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
	PagoRepo repo_pago;

	@Autowired
	SolicitudPropiedadRepo repo_solicitud;

	@Autowired
	PropiedadRepo repo_propiedad;
	
	@Autowired
	InquilinoRepo repo_inquilino;

	@Override
	public List<Map<String, Object>> retornarGananciaAños(Integer id, List<JsonGeneral> anios) {
		Arrendero arrendero = new Arrendero();
		arrendero.setIdArrendero(id);
		try {
			List<Pago> pagos = repo_pago.findByArrenderoAndEstado(arrendero, true);
			List<Map<String, Object>> response = new ArrayList<>();
			anios.forEach(json -> {
				List<Double> gananciapormes = new ArrayList<>();
				IntStream.range(0, 12).forEach(mes -> {
					gananciapormes.add(
							pagos.stream().filter(pago -> pago.getFechaConfirmado().getYear() + 1900 == json.getId())
									.filter(pago -> pago.getFechaConfirmado().getMonth() == mes)
									.mapToDouble(pago -> pago.getMonto()).sum());
				});

				Map<String, Object> ganancias = new HashMap<>();
				ganancias.put("anio", json.getItemName());
				ganancias.put("ganancias", gananciapormes);
				response.add(ganancias);
			});
			return response;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> retornaCantidadSolPropiedad(Integer id) {
		List<Map<String, Object>> resp = new ArrayList<>();
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			List<SolicitudPropiedad> listaSolicitudes = repo_solicitud.findByArrendero(arrendero);
			repo_propiedad.findByArrenderoAndConfirmado(arrendero, true).forEach(propiedad -> {
				Long cantidad = listaSolicitudes.stream()
						.filter(sol -> sol.getPropiedad().getIdPropiedad() == propiedad.getIdPropiedad())
						.map(sol -> sol.getPropiedad())
						.count();
				Map<String,Object> response = new HashMap<>();
				response.put("propiedad",propiedad.getAlias());
				response.put("nrosol",cantidad);
				resp.add(response);
			});
			return resp;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long cantidadInquilinosAlDia(Integer id) {
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			return repo_inquilino.findByArrenderoAndEstadoPago(arrendero,true)
					.stream().count();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long cantidadInquilinosDeudores(Integer id) {
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			return repo_inquilino.findByArrenderoAndEstadoPago(arrendero,false)
					.stream().count();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long cantidadPagosPorConfirmar(Integer id) {
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			return repo_pago.findByArrenderoAndEstadoAndRechazado(arrendero, false,false)
					.stream().count();
			
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long cantidadSolicitudesPendientes(Integer id) {
		try {
			Arrendero arrendero = new Arrendero();
			arrendero.setIdArrendero(id);
			return repo_solicitud.findByArrenderoAndEstado(arrendero, 2)
					.stream().count();
		} catch (Exception e) {
			throw e;
		}
	}

}
