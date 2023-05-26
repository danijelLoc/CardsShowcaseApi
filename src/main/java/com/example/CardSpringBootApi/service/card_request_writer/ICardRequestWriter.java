package com.example.CardSpringBootApi.service.card_request_writer;

import java.util.List;

public interface ICardRequestWriter {
    void updateCardRequestFile(String cardRequestPath, List<String> userDescriptionElements, Boolean createIfNonExistent);
}
