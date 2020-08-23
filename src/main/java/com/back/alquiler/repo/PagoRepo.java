package com.back.alquiler.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.alquiler.models.Arrendero;
import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Pago;
import com.back.alquiler.models.Renta;

public interface PagoRepo extends JpaRepository<Pago,Integer> {
	
	List<Pago> findByArrenderoAndEstadoAndRechazado(Arrendero arrendero, Boolean estado, Boolean rechazado);
	List<Pago> findByRenta(Renta renta);
	List<Pago> findByInquilino(Inquilino inquilino);
	List<Pago> findByArrenderoAndEstado(Arrendero arrendero, Boolean estado);
}
