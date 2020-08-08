package com.back.alquiler.batch;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.back.alquiler.models.Inquilino;
import com.back.alquiler.models.Renta;
import com.back.alquiler.repo.InquilinoRepo;
import com.back.alquiler.repo.RentaRepo;

public class RentaStepTasklet implements Tasklet, StepExecutionListener {

	@Autowired
	RentaRepo repoRenta;

	@Autowired
	InquilinoRepo repoInquilino;

	List<Renta> rentasVencidas;

	private static final Logger LOG = LoggerFactory.getLogger(RentaStepTasklet.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		LOG.info("Analizando las rentas sin pagar. . .");
		rentasVencidas = repoRenta.findAll().stream().filter(renta -> renta.getEstado() != 1)
				.collect(Collectors.toList());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOG.info("La verificacion de rentas vencidas ha terminado. Estado: " + stepExecution.getStatus());
		if (stepExecution.getStatus() == BatchStatus.COMPLETED) {
			return ExitStatus.COMPLETED;
		} else {
			if (stepExecution.getStatus() == BatchStatus.FAILED)
				return ExitStatus.FAILED;
			else {
				if (stepExecution.getStatus() == BatchStatus.STOPPED)
					return ExitStatus.STOPPED;
				else
					return ExitStatus.UNKNOWN;
			}
		}
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		rentasVencidas.forEach(renta -> {
			LocalDate now = LocalDate.now();
			LocalDate fechaFin = Instant.ofEpochMilli(renta.getFechaFinRenta().getTime()).atZone(ZoneId.systemDefault())
					.toLocalDate();
			if (fechaFin.isBefore(now)) {
				Double tasaRecargo = renta.getInquilino().getPropiedad().getCondicionPago().getTasaRecargo()/100.0;
				Double montoRenta = renta.getInquilino().getPropiedad().getCondicionPago().getPrecio();
				renta.setEstado(2);
				renta.setImporteAtrasado(renta.getImporteAtrasado() + montoRenta * tasaRecargo);
			}
			repoRenta.save(renta);
		});

		rentasVencidas.stream().filter(renta -> renta.getEstado() == 2).map(renta -> renta.getInquilino())
				.collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Inquilino::getIdInquilino))),
						ArrayList::new))
				.forEach(inquilino -> {
					inquilino.setEstadoPago(false);
					repoInquilino.save(inquilino);
				});
		return RepeatStatus.FINISHED;
	}

}
