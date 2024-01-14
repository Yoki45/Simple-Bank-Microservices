package com.microservice.cards.service;

import com.microservice.cards.dto.CardsDTO;

public interface IcardsService {

    void createCard(String mobileNumber);

    CardsDTO fetchCard(String mobileNumber);

    boolean updateCard(CardsDTO cardsDto);

    boolean deleteCard(String mobileNumber);
}
