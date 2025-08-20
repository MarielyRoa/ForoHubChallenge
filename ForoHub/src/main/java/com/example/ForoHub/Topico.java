package com.example.ForoHub;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private String curso;

    private LocalDateTime fechaCreacion;
    private String estado; // abierto, cerrado, etc.

    private Long autorId; // puedes usar solo el ID del autor si no quieres relacionarlo con otra entidad

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
        estado = "abierto";
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getAutorId() { return autorId; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }
}
