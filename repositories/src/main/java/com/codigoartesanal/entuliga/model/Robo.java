package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Robo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="robo_id_seq")
    @SequenceGenerator(name="robo_id_seq", sequenceName="robo_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    private Integer minuto;
    private Integer segundo;
    @Enumerated(EnumType.STRING)
    private OrigenEstadistica origen;
    @ManyToOne
    @JoinColumn(name = "robador_id", nullable = false)
    private TorneoJugador robador;
    @ManyToOne
    @JoinColumn(name = "perdedor_id", nullable = false)
    private TorneoJugador perdedor;

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

    public OrigenEstadistica getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenEstadistica origen) {
        this.origen = origen;
    }

    public TorneoJugador getRobador() {
        return robador;
    }

    public void setRobador(TorneoJugador robador) {
        this.robador = robador;
    }

    public TorneoJugador getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(TorneoJugador perdedor) {
        this.perdedor = perdedor;
    }

    public String getTiempoDescripcion(){
        return " " + minuto + " : " + segundo;
    }

    @Override
    public String toString() {
        return "Robo{" +
                "id=" + id +
                ", partido=" + partido +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", origen=" + origen +
                ", robador=" + robador +
                ", perdedor=" + perdedor +
                '}';
    }
}
