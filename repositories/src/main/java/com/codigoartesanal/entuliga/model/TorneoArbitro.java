package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 22/05/15.
 */
@Entity
public class TorneoArbitro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="torneoarbitro_id_seq")
    @SequenceGenerator(name="torneoarbitro_id_seq", sequenceName="torneoarbitro_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
    @ManyToOne
    @JoinColumn(name = "arbitro_id", nullable = false)
    private Arbitro arbitro;
    @Column(name = "status_arbitro")
    @Enumerated(EnumType.STRING)
    private StatusArbitro statusArbitro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public StatusArbitro getStatusArbitro() {
        return statusArbitro;
    }

    public void setStatusArbitro(StatusArbitro statusArbitro) {
        this.statusArbitro = statusArbitro;
    }

    @Override
    public String toString() {
        return "TorneoArbitro{" +
                "id=" + id +
                ", torneo=" + torneo +
                ", arbitro=" + arbitro +
                ", statusArbitro=" + statusArbitro +
                '}';
    }
}
