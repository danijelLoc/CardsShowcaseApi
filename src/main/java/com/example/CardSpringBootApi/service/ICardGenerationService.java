package com.example.CardSpringBootApi.service;

import com.example.CardSpringBootApi.model.User;

public interface ICardGenerationService {
    void createNewCardRequest(long oib);
    void cancelCardRequest(long oib);
    void inactivateCardRequestFileIfExistent(User user);
}
