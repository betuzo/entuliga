package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 8/05/15.
 */
@Entity
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="liga_id_seq")
    @SequenceGenerator(name="liga_id_seq", sequenceName="liga_id_seq")
    private Long id;
    private String nombre;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "geoLocation_id", nullable = false)
    private GeoLocation geoLocation;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
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
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id=" + id +
                ", geoLocation=" + geoLocation +
                '}';
    }
}
