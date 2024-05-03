package com.microservices.accounts.service.client;

import com.microservices.accounts.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="loans", fallback = LoansFallBack.class)
public interface LoansFeignClient {

    @GetMapping(value = "api/fetch",consumes = "application/json")
    public ResponseEntity<LoanDTO>fetchLoanDetails(@RequestParam("mobileNumber") String mobileNumber,@RequestHeader("correlation-id") String correlationId);
}
