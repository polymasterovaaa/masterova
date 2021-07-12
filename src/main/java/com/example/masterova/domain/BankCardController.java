package com.example.masterova.domain;

import com.example.masterova.BankCard;
import com.example.masterova.BankCardRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankCardController {


    private final BankCardRepo bankCardRepo;

    public BankCardController(BankCardRepo bankCardRepo) {
        this.bankCardRepo = bankCardRepo;
    }

    @GetMapping("/cards")
    public List<BankCard> getCards(){
        return bankCardRepo.findAll();
    }

}
