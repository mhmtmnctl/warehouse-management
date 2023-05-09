package com.depo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depo.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
	@Query("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.id = :id")
	Product findByIdWithCategories(@Param("id") Long id);


}
