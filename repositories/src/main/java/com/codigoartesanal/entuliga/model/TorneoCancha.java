package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 22/05/15.
 */
@Entity
public class TorneoCancha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cancha_id", nullable = false)
    private Cancha cancha;
    @Column(name = "status_equipo")
    @Enumerated(EnumType.STRING)
    private StatusCancha statusCancha;

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

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public StatusCancha getStatusCancha() {
        return statusCancha;
    }

    public void setStatusCancha(StatusCancha statusCancha) {
        this.statusCancha = statusCancha;
    }

    @Override
    public String toString() {
        return "TorneoArbitro{" +
                "id=" + id +
                ", torneo=" + torneo +
                ", cancha=" + cancha +
                ", statusCancha=" + statusCancha +
                '}';
    }
}
