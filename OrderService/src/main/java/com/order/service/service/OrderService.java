package com.order.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.order.service.client.InventoryClient;
import com.order.service.client.InventoryRequest;
import com.order.service.client.PaymentClient;
import com.order.service.entity.Order;
import com.order.service.repository.OrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderService {


    
//    private InventoryClient inventoryClient;
//    
//    public OrderService(InventoryClient inventoryClient) {
//    	inventoryClient=this.inventoryClient;
//    }
//    
    
	@Autowired
	private InventoryClient inventoryClient;
	
    @Autowired
    private PaymentClient paymentClient;
    
	@Autowired 
	private OrderRepository oRepo;	

	public Mono<Object> placeOrder(Order request) {
		return inventoryClient.checkStock(request.getProductId(), request.getQuantity())
		        .flatMap(inStock -> {

		            if (!inStock) {
		                return Mono.just("OUT OF STOCK");
		            }
	    return paymentClient.doPayment(request.getTotalAmount())
	        .flatMap(paymentSuccess -> {

	            if (!paymentSuccess) {
	                return Mono.just(false);
	            }
	            
	            
	            Order order = new Order(
	                    request.getProductId(),
	                    request.getQuantity(),
	                    request.getStatus(),
	                    request.getTotalAmount()
	            );

	            return oRepo.save(order)
	                        .thenReturn("Order created");
            });
     });
}
}
