package com.order.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "inventory-service")
//public interface InventoryClient {
//	
//	@GetMapping("/inventory/checkstock")
//	public boolean checkStock(@PathVariable Long productId, @PathVariable int quantity);
//		
//	
//
//}

@Service
public class InventoryClient {

	
    private final WebClient webClient;

    public InventoryClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:9090").build();
    }

    public Mono<Boolean> checkStock(InventoryRequest request) {
        return webClient.post()
                .uri("/inventory/checkstock")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

	public Mono<Boolean> checkStock(Long productId, int quantity) {
		InventoryRequest inv= new InventoryRequest(productId,quantity);
		System.out.println("===================calling webclient=============");
		return checkStock(inv);
		
	}
}
