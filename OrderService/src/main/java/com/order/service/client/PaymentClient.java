package com.order.service.client;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(name = "payment-service")
//public interface PaymentClient {
//
//	@PostMapping("/payment/makePayment")
//	public boolean doPayment(@RequestParam double amount);
//}

@Component
public class PaymentClient {

    private final WebClient webClient;

    public PaymentClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8085")
                .build();
    }

    public Mono<Boolean> doPayment(double amount) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/payment/makePayment")
                        .queryParam("amount", amount)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class);
    }

}
