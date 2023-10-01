package com.comonitech.fraudedetective.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comonitech.fraudedetective.TransactionGenerationService;
import com.comonitech.fraudedetective.repositories.AlerteFraudeRepository;
import com.comonitech.fraudedetective.repositories.ClientRepository;
import com.comonitech.fraudedetective.repositories.TransactionRepository;

@Controller
public class HomeController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AlerteFraudeRepository alerteFraudeRepository;

    @Autowired
    private TransactionGenerationService transactionGenerationService;

    @GetMapping("/")
    public String home(Model model) {
        long numberOfClients = clientRepository.count();
        long numberOfTransactions = transactionRepository.count();
        long numberOfAlertes = alerteFraudeRepository.count();

        model.addAttribute("totalClients", numberOfClients);
        model.addAttribute("totalTransactions", numberOfTransactions);
        model.addAttribute("totalAlertes", numberOfAlertes);

        model.addAttribute("numberOfClients", 1);
        model.addAttribute("numberOfTransactions", 1);

        return "home";
    }

    @PostMapping("/generate-transactions")
    public String generateTransactions(
            @RequestParam int numberOfClients,
            @RequestParam int numberOfTransactions) {
        transactionGenerationService.generateTransactionsAndDetectAlerts(numberOfClients, numberOfTransactions);

        return "redirect:/?success=true";
    }
}
