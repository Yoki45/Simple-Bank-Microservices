package com.microservices.accounts.service.client;


import com.microservices.accounts.dto.CardsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDTO>fetchCardDetails(@RequestParam("mobileNumber") String mobileNumber,@RequestHeader("correlation-id") String correlationId);

}
