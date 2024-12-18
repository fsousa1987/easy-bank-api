package com.francisco.easybankapi.controller;

import com.francisco.easybankapi.model.Customer;
import com.francisco.easybankapi.model.Loans;
import com.francisco.easybankapi.repository.CustomerRepository;
import com.francisco.easybankapi.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        return optionalCustomer.map(customer -> loanRepository
                .findByCustomerIdOrderByStartDtDesc(customer.getId())).orElse(null);
    }

}
