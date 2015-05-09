package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by betuzo on 8/05/15.
 */
@Entity
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "liga_id", nullable = false)
    private Liga liga;
    private String nombre;
    private String descripcion;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @Enumerated(EnumType.STRING)
    private StatusTorneo status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "torneo")
    private Set<Fase> fases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public StatusTorneo getStatus() {
        return status;
    }

    public void setStatus(StatusTorneo status) {
        this.status = status;
    }

    public Set<Fase> getFases() {
        return fases;
    }

    public void setFases(Set<Fase> fases) {
        this.fases = fases;
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "status=" + status +
                ", fechaFin=" + fechaFin +
                ", fechaInicio=" + fechaInicio +
                ", descripcion='" + descripcion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", liga=" + liga +
                ", id=" + id +
                '}';
    }
}
