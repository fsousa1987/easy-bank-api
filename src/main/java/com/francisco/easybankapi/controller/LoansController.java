package com.francisco.easybankapi.controller;

import com.francisco.easybankapi.model.Loans;
import com.francisco.easybankapi.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam long id) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }

}
