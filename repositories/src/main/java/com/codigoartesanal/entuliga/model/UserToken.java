package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by betuzo on 25/01/16.
 */
@Entity
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
    @Enumerated(EnumType.STRING)
    private TipoToken tipo;
    @Column(name = "fecha_vigencia")
    private Date fechaVigencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public UserToken() {
    }

    public UserToken(Long id, User user, String token, TipoToken tipo, Date fechaVigencia) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.tipo = tipo;
        this.fechaVigencia = fechaVigencia;
    }
}
