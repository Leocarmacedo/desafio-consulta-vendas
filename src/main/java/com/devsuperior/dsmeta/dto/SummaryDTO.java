package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SalesProjection;

public class SummaryDTO {

	private String sellerName;
	private Double total;

	public SummaryDTO() {
	}

	public SummaryDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}

	public SummaryDTO(SalesProjection entity) {
		sellerName = entity.getName();
		total = entity.getTotal();
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getTotal() {
		return total;
	}

}
