package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 22/05/15.
 */
@Entity
public class TorneoJugador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "torneo_equipo_id", nullable = false)
    private TorneoEquipo torneoEquipo;
    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;
    @Column(name = "status_jugador")
    @Enumerated(EnumType.STRING)
    private StatusJugador statusJugador;
    @Column(name = "posicion_jugador")
    @Enumerated(EnumType.STRING)
    private PosicionJugador posicionJugador;
    private String numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TorneoEquipo getTorneoEquipo() {
        return torneoEquipo;
    }

    public void setTorneoEquipo(TorneoEquipo torneoEquipo) {
        this.torneoEquipo = torneoEquipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public StatusJugador getStatusJugador() {
        return statusJugador;
    }

    public void setStatusJugador(StatusJugador statusJugador) {
        this.statusJugador = statusJugador;
    }

    public PosicionJugador getPosicionJugador() {
        return posicionJugador;
    }

    public void setPosicionJugador(PosicionJugador posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "TorneoJugador{" +
                "id=" + id +
                ", torneoEquipo=" + torneoEquipo +
                ", jugador=" + jugador +
                ", statusJugador=" + statusJugador +
                ", posicionJugador=" + posicionJugador +
                ", numero=" + numero +
                '}';
    }
}
