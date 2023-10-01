package com.comonitech.fraudedetective.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comonitech.fraudedetective.entities.Tranzaction;
import com.comonitech.fraudedetective.repositories.TransactionRepository;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/list")
    public String listTransactions(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Tranzaction> transactionPage = transactionRepository.findAll(pageable);

        model.addAttribute("transactions", transactionPage.getContent());
        model.addAttribute("page", transactionPage);
        model.addAttribute("currentPage", page);

        return "transactions";
    }
}
