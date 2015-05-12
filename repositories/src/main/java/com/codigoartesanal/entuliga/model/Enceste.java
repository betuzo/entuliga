package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Enceste {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    private Integer minuto;
    private Integer segundo;
    @Enumerated(EnumType.STRING)
    private TipoEnceste tipo;
    @ManyToOne
    @JoinColumn(name = "tirador_id", nullable = false)
    private Jugador tirador;

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

    public TipoEnceste getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnceste tipo) {
        this.tipo = tipo;
    }

    public Jugador getTirador() {
        return tirador;
    }

    public void setTirador(Jugador tirador) {
        this.tirador = tirador;
    }

    @Override
    public String toString() {
        return "Enceste{" +
                "id=" + id +
                ", partido=" + partido +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", tipo=" + tipo +
                ", tirador=" + tirador +
                '}';
    }
}
