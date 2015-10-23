package com.codigoartesanal.entuliga.model.dto;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Jugador;
import com.codigoartesanal.entuliga.model.TorneoEquipo;
import com.codigoartesanal.entuliga.model.TorneoJugador;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by betuzo on 22/10/15.
 */
public class EstadisticaJugadorDTO {

    @JsonIgnore
    private TorneoEquipo torneoEquipo;
    @JsonIgnore
    private Equipo equipo;
    @JsonIgnore
    private TorneoJugador torneoJugador;
    @JsonIgnore
    private Jugador jugador;
    private Long valor;
    private int tipo;
    private Long idTorneoEquipo;
    private Long idEquipo;
    private Long idTorneoJugador;
    private Long idJugador;
    private String nombreJugador;
    private String nombreEquipo;

    public EstadisticaJugadorDTO(
            TorneoEquipo torneoEquipo, Equipo equipo, TorneoJugador torneoJugador,
            Jugador jugador, int tipo, Long valor ) {
        this.torneoEquipo = torneoEquipo;
        this.equipo = equipo;
        this.torneoJugador = torneoJugador;
        this.jugador = jugador;
        this.valor = valor;
        this.tipo = tipo;

        this.idTorneoEquipo = torneoEquipo.getId();
        this.idEquipo = equipo.getId();
        this.idTorneoJugador = torneoJugador.getId();
        this.idJugador = jugador.getId();
        this.nombreEquipo = equipo.getNombre();
        this.nombreJugador = jugador.getNombreCompleto();
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Long getIdTorneoEquipo() {
        return idTorneoEquipo;
    }

    public void setIdTorneoEquipo(Long idTorneoEquipo) {
        this.idTorneoEquipo = idTorneoEquipo;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Long getIdTorneoJugador() {
        return idTorneoJugador;
    }

    public void setIdTorneoJugador(Long idTorneoJugador) {
        this.idTorneoJugador = idTorneoJugador;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
}
