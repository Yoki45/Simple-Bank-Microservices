package com.microservices.accounts.service.client;

import com.microservices.accounts.dto.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class LoansFallBack implements LoansFeignClient{


    @Override
    public ResponseEntity<LoanDTO> fetchLoanDetails(String mobileNumber, String correlationId) {
        return null;
    }
}
