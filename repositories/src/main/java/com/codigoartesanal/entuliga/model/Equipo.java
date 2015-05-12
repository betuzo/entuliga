package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
    private String nombre;
    @Column(name = "alias_equipo")
    private String aliasEquipo;
    @Column(name = "ruta_logo_equipo")
    private String rutaLogoEquipo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo")
    private Set<Jugador> jugadores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAliasEquipo() {
        return aliasEquipo;
    }

    public void setAliasEquipo(String aliasEquipo) {
        this.aliasEquipo = aliasEquipo;
    }

    public String getRutaLogoEquipo() {
        return rutaLogoEquipo;
    }

    public void setRutaLogoEquipo(String rutaLogoEquipo) {
        this.rutaLogoEquipo = rutaLogoEquipo;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", admin=" + admin +
                ", nombre='" + nombre + '\'' +
                ", aliasEquipo='" + aliasEquipo + '\'' +
                ", rutaLogoEquipo='" + rutaLogoEquipo + '\'' +
                '}';
    }
}
