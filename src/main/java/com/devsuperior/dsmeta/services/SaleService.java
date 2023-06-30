package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<ReportDTO> searchReport(String name, String minDate, String maxDate, Pageable pageable) {

		LocalDate convMaxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if (!(maxDate == null)) {
			convMaxDate = LocalDate.parse(maxDate);
		}

		LocalDate convMinDate = convMaxDate.minusYears(1L);
		if (!(minDate == null)) {
			convMinDate = LocalDate.parse(minDate);
		}

		Page<Sale> result = repository.searchReport(name, convMinDate, convMaxDate, pageable);

		return result.map(x -> new ReportDTO(x));
	}

	public List<SummaryDTO> searchSummary(String minDate, String maxDate) {

		LocalDate convMaxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if (!(maxDate == null)) {
			convMaxDate = LocalDate.parse(maxDate);
		}

		LocalDate convMinDate = convMaxDate.minusYears(1L);
		if (!(minDate == null)) {
			convMinDate = LocalDate.parse(minDate);
		}
		
		List<SalesProjection> result = repository.searchSummary(convMinDate, convMaxDate);

		return result.stream().map(x -> new SummaryDTO(x)).collect(Collectors.toList());
	}

}
