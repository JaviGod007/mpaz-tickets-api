package com.escuela.ticketApi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escuela.ticketApi.model.ticketEntity;
import com.escuela.ticketApi.repository.ticketRepository;

@Service
public class ticketService {

    @Autowired
    private ticketRepository repository;

    public List<ticketEntity> listarTickets(String estado, String categoria, String solicitante, String asignadoA) {
        if (estado != null && !estado.isBlank()) {
            return repository.findByEstadoIgnoreCase(estado);
        }
        if (categoria != null && !categoria.isBlank()) {
            return repository.findByCategoriaIgnoreCase(categoria);
        }
        if (solicitante != null && !solicitante.isBlank()) {
            return repository.findBySolicitanteIgnoreCase(solicitante);
        }
        if (asignadoA != null && !asignadoA.isBlank()) {
            return repository.findByAsignadoAIgnoreCase(asignadoA);
        }
        return repository.findAll();
    }

    public Optional<ticketEntity> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public ticketEntity crearTicket(ticketEntity ticket) {
        if (ticket.getEstado() == null || ticket.getEstado().isBlank()) {
            ticket.setEstado("Pendiente");
        }
        if (ticket.getPrioridad() == null || ticket.getPrioridad().isBlank()) {
            ticket.setPrioridad("Media");
        }
        return repository.save(ticket);
    }

    public Optional<ticketEntity> actualizarTicket(Long id, ticketEntity datos) {
        return repository.findById(id).map(ticket -> {
            if (datos.getTitulo() != null && !datos.getTitulo().isBlank()) {
                ticket.setTitulo(datos.getTitulo());
            }
            if (datos.getDescripcion() != null && !datos.getDescripcion().isBlank()) {
                ticket.setDescripcion(datos.getDescripcion());
            }
            if (datos.getCategoria() != null && !datos.getCategoria().isBlank()) {
                ticket.setCategoria(datos.getCategoria());
            }
            if (datos.getUbicacion() != null && !datos.getUbicacion().isBlank()) {
                ticket.setUbicacion(datos.getUbicacion());
            }
            if (datos.getPrioridad() != null && !datos.getPrioridad().isBlank()) {
                ticket.setPrioridad(datos.getPrioridad());
            }
            if (datos.getEstado() != null && !datos.getEstado().isBlank()) {
                ticket.setEstado(datos.getEstado());
            }
            if (datos.getAsignadoA() != null && !datos.getAsignadoA().isBlank()) {
                ticket.setAsignadoA(datos.getAsignadoA());
            }
            if (datos.getFotoUrl() != null && !datos.getFotoUrl().isBlank()) {
                ticket.setFotoUrl(datos.getFotoUrl());
            }

            if (datos.getComentarios() != null && !datos.getComentarios().isBlank()) {
                String marcaTiempo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                String nuevoComentario = "[" + marcaTiempo + "] " + datos.getComentarios();

                if (ticket.getComentarios() == null || ticket.getComentarios().isBlank()) {
                    ticket.setComentarios(nuevoComentario);
                } else {
                    ticket.setComentarios(ticket.getComentarios() + "\n" + nuevoComentario);
                }
            }

            return repository.save(ticket);
        });
    }

    public boolean eliminarTicket(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}