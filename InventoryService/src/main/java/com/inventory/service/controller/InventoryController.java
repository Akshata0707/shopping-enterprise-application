package com.inventory.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.service.InventoryService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService invServ;
	
	@GetMapping("/checkStock")
	public Mono<Boolean> checkStock(@RequestBody InventoryRequest inv) {
		return invServ.checkStock(inv.getProductId(),inv.getQuantity());
		 
		
	}

}
