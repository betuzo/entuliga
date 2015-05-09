package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 8/05/15.
 */
@Entity
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    private String calle;
    @Column(name = "no_exterior")
    private String noExterior;
    private Colonia colonia;
    private String telefono;
    private User admin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "liga")
    private Set<Torneo> torneos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExterior() {
        return noExterior;
    }

    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<Torneo> getTorneos() {
        return torneos;
    }

    public void setTorneos(Set<Torneo> torneos) {
        this.torneos = torneos;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "telefono='" + telefono + '\'' +
                ", colonia=" + colonia +
                ", noExterior='" + noExterior + '\'' +
                ", calle='" + calle + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
