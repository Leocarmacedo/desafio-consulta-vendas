package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(nativeQuery = true, value = "SELECT sa.* "
			+ "FROM tb_sales sa "
			+ "JOIN tb_seller s ON sa.seller_id = s.id "
			+ "WHERE (sa.date BETWEEN :minDate AND :maxDate) "
			+ "AND LOWER(s.name) LIKE CONCAT('%', LOWER(:sellerName), '%')")
	Page<Sale> searchReport(String sellerName, LocalDate minDate, LocalDate maxDate, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT s.name, SUM(sa.amount) as total "
			+ "FROM tb_seller s "
			+ "JOIN tb_sales sa "
			+ "ON s.id = sa.seller_id "
			+ "WHERE sa.date BETWEEN :convMinDate AND :convMaxDate "
			+ "GROUP BY s.name;")
	List<SalesProjection> searchSummary(LocalDate convMinDate, LocalDate convMaxDate);

}
