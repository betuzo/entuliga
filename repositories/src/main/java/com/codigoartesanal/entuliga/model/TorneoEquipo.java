package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 20/05/15.
 */
@Entity
@Table(	name = "TORNEOEQUIPO",
        uniqueConstraints = @UniqueConstraint(columnNames = { "torneo_id", "equipo_id" }))
public class TorneoEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="torneoequipo_id_seq")
    @SequenceGenerator(name="torneoequipo_id_seq", sequenceName="torneoequipo_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;
    @Column(name = "status_equipo")
    @Enumerated(EnumType.STRING)
    private StatusEquipo statusEquipo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "torneoEquipo")
    private Set<TorneoJugador> jugadores;

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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public StatusEquipo getStatusEquipo() {
        return statusEquipo;
    }

    public void setStatusEquipo(StatusEquipo statusEquipo) {
        this.statusEquipo = statusEquipo;
    }

    public Set<TorneoJugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<TorneoJugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "TorneoEquipo{" +
                "id=" + id +
                ", torneo=" + torneo +
                ", equipo=" + equipo +
                ", statusEquipo=" + statusEquipo +
                '}';
    }
}
