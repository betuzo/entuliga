package com.codigoartesanal.entuliga.model.dto;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Jugador;
import com.codigoartesanal.entuliga.model.TorneoEquipo;
import com.codigoartesanal.entuliga.model.TorneoJugador;

/**
 * Created by betuzo on 22/10/15.
 */
public class EstadisticaJugadorDTO {

    private TorneoEquipo torneoEquipo;
    private Equipo equipo;
    private TorneoJugador torneoJugador;
    private Jugador jugador;
    private Long valor;

    public EstadisticaJugadorDTO(
            TorneoEquipo torneoEquipo, Equipo equipo, TorneoJugador torneoJugador, Jugador jugador, Long valor) {
        this.torneoEquipo = torneoEquipo;
        this.equipo = equipo;
        this.torneoJugador = torneoJugador;
        this.jugador = jugador;
        this.valor = valor;
    }

    public TorneoEquipo getTorneoEquipo() {
        return torneoEquipo;
    }

    public void setTorneoEquipo(TorneoEquipo torneoEquipo) {
        this.torneoEquipo = torneoEquipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public TorneoJugador getTorneoJugador() {
        return torneoJugador;
    }

    public void setTorneoJugador(TorneoJugador torneoJugador) {
        this.torneoJugador = torneoJugador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}
