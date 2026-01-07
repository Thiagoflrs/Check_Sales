package com.devsuperior.desafio.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio.dto.SaleMinDTO;
import com.devsuperior.desafio.dto.SaleReportDTO;
import com.devsuperior.desafio.dto.SaleSummaryDTO;
import com.devsuperior.desafio.entities.Sale;
import com.devsuperior.desafio.repositories.SaleRepository;

@Service
public class SaleService {
	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<SaleReportDTO> getReport(
	            String minDate,
	            String maxDate,
	            String sellerName,
	            Pageable pageable) {

	        LocalDate today = LocalDate.ofInstant(
	                Instant.now(),
	                ZoneId.systemDefault()
	        );

	        LocalDate max = maxDate.isEmpty()
	                ? today
	                : LocalDate.parse(maxDate);

	        LocalDate min = minDate.isEmpty()
	                ? max.minusYears(1L)
	                : LocalDate.parse(minDate);

	        String name = sellerName.isEmpty() ? "" : sellerName;

	        Page<Sale> result = repository.searchReport(min, max, name, pageable);

	        return result.map(SaleReportDTO::new);
	    }
	
	@Transactional(readOnly = true)
	public List<SaleSummaryDTO> getSummary(
	        String minDate,
	        String maxDate) {

	    LocalDate today = LocalDate.ofInstant(
	            Instant.now(),
	            ZoneId.systemDefault()
	    );

	    LocalDate max = maxDate.isEmpty()
	            ? today
	            : LocalDate.parse(maxDate);

	    LocalDate min = minDate.isEmpty()
	            ? max.minusYears(1L)
	            : LocalDate.parse(minDate);

	    return repository.searchSummary(min, max);
	}


}
