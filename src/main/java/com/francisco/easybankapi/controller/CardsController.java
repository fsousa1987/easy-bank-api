package com.francisco.easybankapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/my-cards")
    public String getCardsDetails() {
        return "Here are the card details from the DB";
    }

}
