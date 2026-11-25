package com.escuela.ticketApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.escuela.ticketApi.model.ticketEntity;
import com.escuela.ticketApi.service.ticketService;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class ticketController {

    @Autowired
    private ticketService service;

    @GetMapping
    public ResponseEntity<List<ticketEntity>> listarTickets(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String solicitante,
            @RequestParam(required = false) String asignadoA) {
        return ResponseEntity.ok(service.listarTickets(estado, categoria, solicitante, asignadoA));
    }

    @GetMapping("/")
    public String inicio() {
        return "API de Tickets activa. Usa /api/tickets para consultar o crear tickets.";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ticketEntity> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ticketEntity> crearTicket(@RequestBody ticketEntity ticket) {
        ticketEntity creado = service.crearTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ticketEntity> actualizarTicket(@PathVariable Long id, @RequestBody ticketEntity datos) {
        return service.actualizarTicket(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable Long id) {
        if (!service.eliminarTicket(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}