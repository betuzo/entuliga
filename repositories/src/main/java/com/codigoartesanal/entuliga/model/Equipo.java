package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="equipo_id_seq")
    @SequenceGenerator(name="equipo_id_seq", sequenceName="equipo_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
    private String nombre;
    @Column(name = "alias_equipo")
    private String aliasEquipo;
    @Column(name = "ruta_logo_equipo")
    private String rutaLogoEquipo;
    @Column(name = "main_color")
    private String mainColor;

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

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
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
