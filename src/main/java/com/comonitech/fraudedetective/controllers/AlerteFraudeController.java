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

import com.comonitech.fraudedetective.entities.AlerteFraude;
import com.comonitech.fraudedetective.repositories.AlerteFraudeRepository;

@Controller
@RequestMapping("/alertes")
public class AlerteFraudeController {

    @Autowired
    private AlerteFraudeRepository alerteFraudeRepository;

    @GetMapping("/list")
    public String listAlertes(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<AlerteFraude> alertePage = alerteFraudeRepository.findAll(pageable);

        model.addAttribute("alertes", alertePage.getContent());
        model.addAttribute("page", alertePage);
        model.addAttribute("currentPage", page);

        return "alertes";
    }
}
