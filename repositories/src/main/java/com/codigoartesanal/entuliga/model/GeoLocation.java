package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class GeoLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String calle;
    @Column(name = "no_exterior")
    private String noExterior;
    @Column(name = "no_interior")
    private String noInterior;
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "colonia_id", nullable = false)
    private Colonia colonia;
    private double longitude;
    private double latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNoInterior() {
        return noInterior;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "GeoLocation{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", noExterior='" + noExterior + '\'' +
                ", noInterior='" + noInterior + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", colonia=" + colonia +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
