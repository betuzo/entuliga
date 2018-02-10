package com.codigoartesanal.entuliga.model;

import javax.persistence.*;

/**
 * Created by betuzo on 8/05/15.
 */
@Entity
public class Colonia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="colonia_id_seq")
    @SequenceGenerator(name="colonia_id_seq", sequenceName="colonia_id_seq")
    private Long id;
    private String nombre;
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;

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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Colonia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", municipio=" + municipio +
                '}';
    }
}
