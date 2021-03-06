package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Enceste {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="enceste_id_seq")
    @SequenceGenerator(name="enceste_id_seq", sequenceName="enceste_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    private Integer minuto;
    private Integer segundo;
    @Enumerated(EnumType.STRING)
    private TipoEnceste tipo;
    @Enumerated(EnumType.STRING)
    private OrigenEstadistica origen;
    @ManyToOne
    @JoinColumn(name = "tirador_id", nullable = false)
    private TorneoJugador tirador;
    private Integer valor;
    @Column(name = "success", nullable = false)
    private boolean success;

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

    public OrigenEstadistica getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenEstadistica origen) {
        this.origen = origen;
    }

    public TorneoJugador getTirador() {
        return tirador;
    }

    public void setTirador(TorneoJugador tirador) {
        this.tirador = tirador;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTiempoDescripcion(){
        return " " + minuto + " : " + segundo;
    }

    @Override
    public String toString() {
        return "Enceste{" +
                "id=" + id +
                ", partido=" + partido +
                ", minuto=" + minuto +
                ", segundo=" + segundo +
                ", tipo=" + tipo +
                ", valor=" + valor +
                ", origen=" + origen +
                ", tirador=" + tirador +
                '}';
    }
}
