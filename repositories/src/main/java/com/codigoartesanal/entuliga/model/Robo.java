package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Robo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    private Integer minuto;
    private Integer segundo;
    @ManyToOne
    @JoinColumn(name = "robador_id", nullable = false)
    private Jugador robador;
    @ManyToOne
    @JoinColumn(name = "perdedor_id", nullable = false)
    private Jugador perdedor;

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

    public Jugador getRobador() {
        return robador;
    }

    public void setRobador(Jugador robador) {
        this.robador = robador;
    }

    public Jugador getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(Jugador perdedor) {
        this.perdedor = perdedor;
    }

    @Override
    public String toString() {
        return "Robo{" +
                "id=" + id +
                ", partido=" + partido +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", robador=" + robador +
                ", perdedor=" + perdedor +
                '}';
    }
}
