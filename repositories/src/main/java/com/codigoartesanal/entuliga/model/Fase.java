package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 8/05/15.
 */
@Entity
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
}
