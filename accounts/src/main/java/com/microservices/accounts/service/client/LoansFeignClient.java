package com.microservices.accounts.service.client;

import com.microservices.accounts.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "api/fetch",consumes = "application/json")
    public ResponseEntity<LoanDTO>fetchLoanDetails(@RequestParam String mobileNumber);
}
