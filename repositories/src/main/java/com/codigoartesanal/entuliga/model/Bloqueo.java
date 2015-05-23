package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Bloqueo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    private Integer minuto;
    private Integer segundo;
    @ManyToOne
    @JoinColumn(name = "bloquea_id", nullable = false)
    private TorneoJugador bloquea;
    @ManyToOne
    @JoinColumn(name = "bloqueado_id", nullable = false)
    private TorneoJugador bloqueado;

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

    public TorneoJugador getBloquea() {
        return bloquea;
    }

    public void setBloquea(TorneoJugador bloquea) {
        this.bloquea = bloquea;
    }

    public TorneoJugador getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(TorneoJugador bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public String toString() {
        return "Bloqueo{" +
                "id=" + id +
                ", partido=" + partido +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", bloquea=" + bloquea +
                ", bloqueado=" + bloqueado +
                '}';
    }
}
