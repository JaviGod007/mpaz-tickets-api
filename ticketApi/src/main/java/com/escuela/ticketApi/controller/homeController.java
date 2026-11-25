package com.escuela.ticketApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping("/")
    public String inicio() {
        return "API de Tickets activa. Usa /api/tickets para consultar o crear tickets.";
    }
}