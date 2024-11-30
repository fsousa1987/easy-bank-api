package com.francisco.easybankapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/my-loans")
    public String getLoansDetails() {
        return "Here are the loans details from the DB";
    }

}
