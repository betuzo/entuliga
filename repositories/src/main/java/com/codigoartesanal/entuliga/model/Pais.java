package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 8/05/15.
 */
@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String abreviatura;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pais")
    private Set<Estado> estados;

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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Set<Estado> getEstados() {
        return estados;
    }

    public void setEstados(Set<Estado> estados) {
        this.estados = estados;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                '}';
    }
}
