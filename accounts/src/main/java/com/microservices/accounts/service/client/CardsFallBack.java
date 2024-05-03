package com.microservices.accounts.service.client;

import com.microservices.accounts.dto.CardsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements  CardsFeignClient{


    @Override
    public ResponseEntity<CardsDTO> fetchCardDetails(String mobileNumber, String correlationId) {
        return null;
    }
}
