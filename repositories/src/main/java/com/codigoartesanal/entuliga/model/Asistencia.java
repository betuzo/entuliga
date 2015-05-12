package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    private Integer minuto;
    private Integer segundo;
    @ManyToOne
    @JoinColumn(name = "asiste_id", nullable = false)
    private Jugador asiste;
    @ManyToOne
    @JoinColumn(name = "asistido_id", nullable = false)
    private Jugador asistido;

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

    public Jugador getAsiste() {
        return asiste;
    }

    public void setAsiste(Jugador asiste) {
        this.asiste = asiste;
    }

    public Jugador getAsistido() {
        return asistido;
    }

    public void setAsistido(Jugador asistido) {
        this.asistido = asistido;
    }

    @Override
    public String toString() {
        return "Asistencia{" +
                "id=" + id +
                ", partido=" + partido +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", asiste=" + asiste +
                ", asistido=" + asistido +
                '}';
    }
}
