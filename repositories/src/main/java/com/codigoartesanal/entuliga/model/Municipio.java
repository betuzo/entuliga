package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 8/05/15.
 */
@Entity
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="municipio_id_seq")
    @SequenceGenerator(name="municipio_id_seq", sequenceName="municipio_id_seq")
    private Long id;
    private String nombre;
    @Column(name = "nombre_oficial")
    private String nombreOficial;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "municipio")
    private Set<Colonia> colonias;

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

    public String getNombreOficial() {
        return nombreOficial;
    }

    public void setNombreOficial(String nombreOficial) {
        this.nombreOficial = nombreOficial;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Colonia> getColonias() {
        return colonias;
    }

    public void setColonias(Set<Colonia> colonias) {
        this.colonias = colonias;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nombreOficial='" + nombreOficial + '\'' +
                ", estado=" + estado +
                '}';
    }
}
