package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Cancha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
    private String nombre;
    private String alias;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "geoLocation_id", nullable = false)
    private GeoLocation geoLocation;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    @Override
    public String toString() {
        return "Cancha{" +
                "id=" + id +
                ", admin=" + admin +
                ", nombre='" + nombre + '\'' +
                ", alias='" + alias + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", geoLocation=" + geoLocation +
                '}';
    }
}
