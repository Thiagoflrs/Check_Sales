package com.devsuperior.desafio.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.desafio.dto.SaleSummaryDTO;
import com.devsuperior.desafio.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@EntityGraph(attributePaths = "seller")
    @Query("""
        SELECT obj
        FROM Sale obj
        JOIN obj.seller seller
        WHERE obj.date BETWEEN :minDate AND :maxDate
        AND UPPER(seller.name) LIKE UPPER(CONCAT('%', :sellerName, '%'))
    """)
    Page<Sale> searchReport(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate,
            @Param("sellerName") String sellerName,
            Pageable pageable);
    
    
    @Query("""
    	    SELECT new com.devsuperior.desafio.dto.SaleSummaryDTO(
    	        obj.seller.name,
    	        SUM(obj.amount)
    	    )
    	    FROM Sale obj
    	    WHERE obj.date BETWEEN :min AND :max
    	    GROUP BY obj.seller.id, obj.seller.name
    	""")
    	List<SaleSummaryDTO> searchSummary(
    	        @Param("min") LocalDate min,
    	        @Param("max") LocalDate max
    	);

}
