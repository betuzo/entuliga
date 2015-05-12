package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class PartidoArbitro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    @ManyToOne
    @JoinColumn(name = "arbitro_id", nullable = false)
    private Arbitro arbitro;
    @Column(name = "tipo_arbitro")
    @Enumerated(EnumType.STRING)
    private TipoArbitro tipoArbitro;

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

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public TipoArbitro getTipoArbitro() {
        return tipoArbitro;
    }

    public void setTipoArbitro(TipoArbitro tipoArbitro) {
        this.tipoArbitro = tipoArbitro;
    }

    @Override
    public String toString() {
        return "PartidoArbitro{" +
                "id=" + id +
                ", partido=" + partido +
                ", arbitro=" + arbitro +
                ", tipoArbitro=" + tipoArbitro +
                '}';
    }
}
