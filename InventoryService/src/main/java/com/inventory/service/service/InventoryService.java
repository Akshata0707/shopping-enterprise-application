package com.inventory.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventory.service.Entity.Inventory;
import com.inventory.service.repository.InventoryServiceRepo;

import reactor.core.publisher.Mono;

@Service
public class InventoryService {

	@Autowired
	private InventoryServiceRepo repo;
	
	public Mono<Boolean> checkStock(Long productId, int quantity){
		
		return  repo.findByProductId(productId)
	            .map(inv -> inv.getAvailableQuantity() >= quantity)
	            .defaultIfEmpty(false);
    }

}
