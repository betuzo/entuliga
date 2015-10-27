package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class PartidoArbitro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="partidoarbitro_id_seq")
    @SequenceGenerator(name="partidoarbitro_id_seq", sequenceName="partidoarbitro_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;
    @ManyToOne
    @JoinColumn(name = "torneo_arbitro_id", nullable = false)
    private TorneoArbitro arbitro;
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

    public TorneoArbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(TorneoArbitro arbitro) {
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
