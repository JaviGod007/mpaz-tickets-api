package com.escuela.ticketApi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.escuela.ticketApi.model.ticketEntity;

@Repository
public interface ticketRepository extends JpaRepository<ticketEntity, Long> {
    List<ticketEntity> findByEstadoIgnoreCase(String estado);
    List<ticketEntity> findByCategoriaIgnoreCase(String categoria);
    List<ticketEntity> findBySolicitanteIgnoreCase(String solicitante);
    List<ticketEntity> findByAsignadoAIgnoreCase(String asignadoA);
}