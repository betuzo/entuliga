package com.codigoartesanal.entuliga.model.dto;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Jugador;
import com.codigoartesanal.entuliga.model.TorneoEquipo;
import com.codigoartesanal.entuliga.model.TorneoJugador;

/**
 * Created by betuzo on 22/10/15.
 */
public class EstadisticaJugadorDTO {

    private Long valor;
    private int tipo;
    private Long idTorneoEquipo;
    private Long idEquipo;
    private Long idTorneoJugador;
    private Long idJugador;
    private String nombreJugador;
    private String fotoJugador;
    private String nombreEquipo;
    private String logoEquipo;

    public EstadisticaJugadorDTO(
            TorneoEquipo torneoEquipo, Equipo equipo, TorneoJugador torneoJugador,
            Jugador jugador, int tipo, Long valor ) {
        this.valor = valor;
        this.tipo = tipo;

        this.idTorneoEquipo = torneoEquipo.getId();
        this.idEquipo = equipo.getId();
        this.idTorneoJugador = torneoJugador.getId();
        this.idJugador = jugador.getId();
        this.nombreEquipo = equipo.getNombre();
        this.logoEquipo = equipo.getRutaLogoEquipo();
        this.nombreJugador = jugador.getNombreCompleto();
        this.fotoJugador = jugador.getRutaFoto();
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

    public String getFotoJugador() {
        return fotoJugador;
    }

    public void setFotoJugador(String fotoJugador) {
        this.fotoJugador = fotoJugador;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getLogoEquipo() {
        return logoEquipo;
    }

    public void setLogoEquipo(String logoEquipo) {
        this.logoEquipo = logoEquipo;
    }
}
