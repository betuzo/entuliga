package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="movimiento_id_seq")
    @SequenceGenerator(name="movimiento_id_seq", sequenceName="movimiento_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    private Integer minuto;
    private Integer segundo;
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;
    @Enumerated(EnumType.STRING)
    private OrigenEstadistica origen;
    @ManyToOne
    @JoinColumn(name = "entra_id", nullable = false)
    private TorneoJugador entra;
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = true)
    private TorneoJugador sale;

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

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public OrigenEstadistica getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenEstadistica origen) {
        this.origen = origen;
    }

    public TorneoJugador getEntra() {
        return entra;
    }

    public void setEntra(TorneoJugador entra) {
        this.entra = entra;
    }

    public TorneoJugador getSale() {
        return sale;
    }

    public void setSale(TorneoJugador sale) {
        this.sale = sale;
    }

    public String getTiempoDescripcion(){
        return " " + minuto + " : " + segundo;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id=" + id +
                ", partido=" + partido +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", tipo=" + tipo +
                ", origen=" + origen +
                ", entra=" + entra +
                ", sale=" + sale +
                '}';
    }
}
