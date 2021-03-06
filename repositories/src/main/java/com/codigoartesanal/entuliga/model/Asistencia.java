package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="asistencia_id_seq")
    @SequenceGenerator(name="asistencia_id_seq", sequenceName="asistencia_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    @Enumerated(EnumType.STRING)
    private OrigenEstadistica origen;
    private Integer minuto;
    private Integer segundo;
    @ManyToOne
    @JoinColumn(name = "asiste_id", nullable = false)
    private TorneoJugador asiste;
    @ManyToOne
    @JoinColumn(name = "asistido_id", nullable = false)
    private TorneoJugador asistido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public OrigenEstadistica getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenEstadistica origen) {
        this.origen = origen;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Integer getSegundo() {
        return segundo;
    }

    public void setSegundo(Integer segundo) {
        this.segundo = segundo;
    }

    public TorneoJugador getAsiste() {
        return asiste;
    }

    public void setAsiste(TorneoJugador asiste) {
        this.asiste = asiste;
    }

    public TorneoJugador getAsistido() {
        return asistido;
    }

    public void setAsistido(TorneoJugador asistido) {
        this.asistido = asistido;
    }

    public String getTiempoDescripcion(){
        return " " + minuto + " : " + segundo;
    }

    @Override
    public String toString() {
        return "Asistencia{" +
                "id=" + id +
                ", partido=" + partido +
                ", origen=" + origen +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", asiste=" + asiste +
                ", asistido=" + asistido +
                '}';
    }
}
