package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class ReportDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	private String sellerName;

	public ReportDTO() {
	}

	public ReportDTO(Sale entity) {
		id = entity.getId();
		sellerName = entity.getSeller().getName();
		amount = entity.getAmount();
		date = entity.getDate();
	}

	public Long getId() {
		return id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

}
