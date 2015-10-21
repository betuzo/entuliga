package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by betuzo on 8/05/15.
 */
@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="estado_id_seq")
    @SequenceGenerator(name="estado_id_seq", sequenceName="estado_id_seq")
    private Long id;
    private String nombre;
    private String abreviatura;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
    private Set<Municipio> municipios;

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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Set<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Set<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                ", pais=" + pais +
                '}';
    }
}
