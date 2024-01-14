package com.microservice.cards.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CardAlreadyExistsdException extends RuntimeException {


    public CardAlreadyExistsdException(String message){
        super(message);
    }
}
