package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="jornada_id_seq")
    @SequenceGenerator(name="jornada_id_seq", sequenceName="jornada_id_seq")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Fase fase;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jornada")
    private Set<Partido> partidos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Set<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(Set<Partido> partidos) {
        this.partidos = partidos;
    }

    @Override
    public String toString() {
        return "Jornada{" +
                "fase=" + fase +
                ", nombre='" + nombre + '\'' +
                ", torneo=" + torneo +
                ", id=" + id +
                '}';
    }
}
