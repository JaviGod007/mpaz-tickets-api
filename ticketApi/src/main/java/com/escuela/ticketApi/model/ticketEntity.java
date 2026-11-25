package com.escuela.ticketApi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@AllArgsConstructor
@NoArgsConstructor 
@Getter 
@Setter 
@Table (name = "tickets_remoto")
public class ticketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    // Infraestructura, Tecnología, Mobiliario, Otra
    private String categoria;

    // Ubicación manual dentro de la escuela (sin GPS)
    private String ubicacion;

    // Baja, Media, Alta, Urgente
    private String prioridad;

    // Pendiente, Revisado, Asignado, En proceso, Solucionado, Cerrado, Cancelado
    private String estado;

    // Nombres o códigos ficticios/anonimizados
    private String solicitante;
    private String asignadoA;

    @Column(length = 2000)
    private String comentarios;

    @Column(length = 1000)
    private String fotoUrl;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}


