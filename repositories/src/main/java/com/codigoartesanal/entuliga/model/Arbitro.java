package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Arbitro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
    @ManyToOne
    @JoinColumn(name = "liga_id", nullable = false)
    private Liga liga;
    private String nombre;
    private String paterno;
    private String materno;
    @Column(name = "ruta_foto")
    private String rutaFoto;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
}
