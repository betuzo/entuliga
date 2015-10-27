package com.codigoartesanal.entuliga.model.view;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoEquipo;

import javax.persistence.*;

/**
 * Created by betuzo on 21/10/15.
 */
@Entity
@Table(	name = "ClasificacionView" )
@IdClass(ClasificacionId.class)
public class Clasificacion {

    @Id
    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = false)
    private TorneoEquipo equipo;
    @Id
    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
    private String nombre;

    @Column(name = "puntos_local")
    private Integer puntosLocal;
    @Column(name = "puntos_local_contra")
    private Integer puntosLocalContra;
    @Column(name = "ganados_local")
    private Integer ganadosLocal;
    @Column(name = "perdidos_local")
    private Integer perdidosLocal;
    @Column(name = "jugados_local")
    private Integer jugadosLocal;

    @Column(name = "puntos_visita")
    private Integer puntosVisita;
    @Column(name = "puntos_visita_contra")
    private Integer puntosVisitaContra;
    @Column(name = "ganados_visita")
    private Integer ganadosVisita;
    @Column(name = "perdidos_visita")
    private Integer perdidosVisita;
    @Column(name = "jugados_visita")
    private Integer jugadosVisita;

    @Column(name = "total_puntos_favor")
    private Integer totalPuntosFavor;
    @Column(name = "total_puntos_contra")
    private Integer totalPuntosContra;
    @Column(name = "diferencia_puntos")
    private Integer diferenciaPuntos;
    @Column(name = "total_ganados")
    private Integer totalGanados;
    @Column(name = "total_perdidos")
    private Integer totalPerdidos;
    @Column(name = "total_jugados")
    private Integer totalJugados;

    public TorneoEquipo getEquipo() {
        return equipo;
    }

    public void setEquipo(TorneoEquipo equipo) {
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Integer getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(Integer puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public Integer getPuntosLocalContra() {
        return puntosLocalContra;
    }

    public void setPuntosLocalContra(Integer puntosLocalContra) {
        this.puntosLocalContra = puntosLocalContra;
    }

    public Integer getGanadosLocal() {
        return ganadosLocal;
    }

    public void setGanadosLocal(Integer ganadosLocal) {
        this.ganadosLocal = ganadosLocal;
    }

    public Integer getPerdidosLocal() {
        return perdidosLocal;
    }

    public void setPerdidosLocal(Integer perdidosLocal) {
        this.perdidosLocal = perdidosLocal;
    }

    public Integer getJugadosLocal() {
        return jugadosLocal;
    }

    public void setJugadosLocal(Integer jugadosLocal) {
        this.jugadosLocal = jugadosLocal;
    }

    public Integer getPuntosVisita() {
        return puntosVisita;
    }

    public void setPuntosVisita(Integer puntosVisita) {
        this.puntosVisita = puntosVisita;
    }

    public Integer getPuntosVisitaContra() {
        return puntosVisitaContra;
    }

    public void setPuntosVisitaContra(Integer puntosVisitaContra) {
        this.puntosVisitaContra = puntosVisitaContra;
    }

    public Integer getGanadosVisita() {
        return ganadosVisita;
    }

    public void setGanadosVisita(Integer ganadosVisita) {
        this.ganadosVisita = ganadosVisita;
    }

    public Integer getPerdidosVisita() {
        return perdidosVisita;
    }

    public void setPerdidosVisita(Integer perdidosVisita) {
        this.perdidosVisita = perdidosVisita;
    }

    public Integer getJugadosVisita() {
        return jugadosVisita;
    }

    public void setJugadosVisita(Integer jugadosVisita) {
        this.jugadosVisita = jugadosVisita;
    }

    public Integer getTotalPuntosFavor() {
        return totalPuntosFavor;
    }

    public void setTotalPuntosFavor(Integer totalPuntosFavor) {
        this.totalPuntosFavor = totalPuntosFavor;
    }

    public Integer getTotalPuntosContra() {
        return totalPuntosContra;
    }

    public void setTotalPuntosContra(Integer totalPuntosContra) {
        this.totalPuntosContra = totalPuntosContra;
    }

    public Integer getDiferenciaPuntos() {
        return diferenciaPuntos;
    }

    public void setDiferenciaPuntos(Integer diferenciaPuntos) {
        this.diferenciaPuntos = diferenciaPuntos;
    }

    public Integer getTotalGanados() {
        return totalGanados;
    }

    public void setTotalGanados(Integer totalGanados) {
        this.totalGanados = totalGanados;
    }

    public Integer getTotalPerdidos() {
        return totalPerdidos;
    }

    public void setTotalPerdidos(Integer totalPerdidos) {
        this.totalPerdidos = totalPerdidos;
    }

    public Integer getTotalJugados() {
        return totalJugados;
    }

    public void setTotalJugados(Integer totalJugados) {
        this.totalJugados = totalJugados;
    }

    @Override
    public String toString() {
        return "Clasificacion{" +
                "equipo=" + equipo +
                ", nombre='" + nombre + '\'' +
                ", torneo=" + torneo +
                ", puntosLocal=" + puntosLocal +
                ", puntosLocalContra=" + puntosLocalContra +
                ", ganadosLocal=" + ganadosLocal +
                ", perdidosLocal=" + perdidosLocal +
                ", jugadosLocal=" + jugadosLocal +
                ", puntosVisita=" + puntosVisita +
                ", puntosVisitaContra=" + puntosVisitaContra +
                ", ganadosVisita=" + ganadosVisita +
                ", perdidosVisita=" + perdidosVisita +
                ", jugadosVisita=" + jugadosVisita +
                ", totalPuntosFavor=" + totalPuntosFavor +
                ", totalPuntosContra=" + totalPuntosContra +
                ", diferenciaPuntos=" + diferenciaPuntos +
                ", totalGanados=" + totalGanados +
                ", totalPerdidos=" + totalPerdidos +
                ", totalJugados=" + totalJugados +
                '}';
    }
}
