package com.example.CardSpringBootApi.controller;

import com.example.CardSpringBootApi.service.ICardGenerationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card-request")
public class CardGenerationController {
    private final ICardGenerationService cardGenerationService;

    public CardGenerationController(ICardGenerationService cardGenerationService) {
        this.cardGenerationService = cardGenerationService;
    }

    @PostMapping("/{oib}")
    public void requestCardGeneration(@PathVariable long oib) {
        cardGenerationService.createNewCardRequest(oib);
    }

    @DeleteMapping("/{oib}")
    public void cancelCardGeneration(@PathVariable long oib) {
        cardGenerationService.cancelCardRequest(oib);
    }


}
