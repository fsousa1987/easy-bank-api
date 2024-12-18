package com.francisco.easybankapi.controller;

import com.francisco.easybankapi.model.Cards;
import com.francisco.easybankapi.model.Customer;
import com.francisco.easybankapi.repository.CardsRepository;
import com.francisco.easybankapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        return optionalCustomer.map(customer -> cardsRepository
                .findByCustomerId(customer.getId())).orElse(null);
    }

}
