package com.inventory.service.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.service.Entity.Inventory;

import reactor.core.publisher.Mono;


@Repository
public interface InventoryServiceRepo extends ReactiveCrudRepository<Inventory, Long>{

	@Query("SELECT availableQuantity FROM Inventory WHERE productId =:productId and availableQuantity=:availableQuantity ")
	Mono<Integer> checkStock(@Param(value = "productId") Long productId, @Param(value = "availableQuantity") int availableQuantity);

	@Query("SELECT  * FROM Inventory WHERE product_id =:productId ")
	Mono<Inventory> findByProductId(Long productId);

	
}
