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
    private String nombre;
    private String paterno;
    private String materno;
    @Column(name = "ruta_foto")
    private String rutaFoto;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
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

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    @Override
    public String toString() {
        return "Arbitro{" +
                "id=" + id +
                ", admin=" + admin +
                ", nombre='" + nombre + '\'' +
                ", paterno='" + paterno + '\'' +
                ", materno='" + materno + '\'' +
                ", rutaFoto='" + rutaFoto + '\'' +
                ", sexo=" + sexo +
                ", fechaRegistro=" + fechaRegistro +
                ", geoLocation=" + geoLocation +
                '}';
    }
}
