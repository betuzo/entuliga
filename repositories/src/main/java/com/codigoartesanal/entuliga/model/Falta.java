package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Falta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    private Integer minuto;
    private Integer segundo;
    @Enumerated(EnumType.STRING)
    private TipoFalta tipo;
    @Enumerated(EnumType.STRING)
    private OrigenEstadistica origen;
    @ManyToOne
    @JoinColumn(name = "recibe_id", nullable = false)
    private TorneoJugador recibe;
    @ManyToOne
    @JoinColumn(name = "infractor_id", nullable = false)
    private TorneoJugador infractor;

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

    public TipoFalta getTipo() {
        return tipo;
    }

    public void setTipo(TipoFalta tipo) {
        this.tipo = tipo;
    }

    public OrigenEstadistica getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenEstadistica origen) {
        this.origen = origen;
    }

    public TorneoJugador getRecibe() {
        return recibe;
    }

    public void setRecibe(TorneoJugador recibe) {
        this.recibe = recibe;
    }

    public TorneoJugador getInfractor() {
        return infractor;
    }

    public void setInfractor(TorneoJugador infractor) {
        this.infractor = infractor;
    }

    public String getTiempoDescripcion(){
        return " " + minuto + " : " + segundo;
    }

    @Override
    public String toString() {
        return "Falta{" +
                "id=" + id +
                ", partido=" + partido +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", tipo=" + tipo +
                ", origen=" + origen +
                ", recibe=" + recibe +
                ", infractor=" + infractor +
                '}';
    }
}
