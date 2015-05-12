package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Fase fase;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jornada")
    private Set<Partido> partidos;
}
