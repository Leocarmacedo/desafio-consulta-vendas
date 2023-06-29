package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(nativeQuery = true, value = "SELECT * "
			+ "FROM tb_sales sa "
			+ "JOIN tb_seller s ON sa.seller_id = s.id "
			+ "WHERE (sa.date BETWEEN :minDate AND :maxDate) "
			+ "AND LOWER(s.name) LIKE CONCAT('%', LOWER(:sellerName), '%')")
	Page<Sale> searchReport(String sellerName, LocalDate minDate, LocalDate maxDate, Pageable pageable);

}
