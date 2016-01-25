package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by betuzo on 25/01/16.
 */
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usertoken_id_seq")
    @SequenceGenerator(name="usertoken_id_seq", sequenceName="usertoken_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
    @Column(name = "token", nullable = false, length = 45)
    private String token;
    @Column(name = "fecha_vigencia")
    private Date fechaVigencia;
}
