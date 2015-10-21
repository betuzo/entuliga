package com.codigoartesanal.entuliga.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by betuzo on 11/05/15.
 */
@Entity
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="partido_id_seq")
    @SequenceGenerator(name="partido_id_seq", sequenceName="partido_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "jornada_id", nullable = false)
    private Jornada jornada;
    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private TorneoEquipo local;
    @Column(name = "puntos_local")
    private Long puntosLocal;
    @ManyToOne
    @JoinColumn(name = "visitante_id", nullable = false)
    private TorneoEquipo visitante;
    @Column(name = "puntos_visitante")
    private Long puntosVisitante;
    @ManyToOne
    @JoinColumn(name = "cancha_id", nullable = false)
    private TorneoCancha cancha;
    private Date horario;
    @Enumerated(EnumType.STRING)
    private StatusPartido status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    private Set<PartidoArbitro> arbitros;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    private Set<Movimiento> movimientos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    private Set<Asistencia> asistencias;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    private Set<Bloqueo> bloqueos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    private Set<Enceste> encestes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    private Set<Falta> faltas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    private Set<Rebote> rebotes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partido")
    private Set<Robo> robos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public TorneoEquipo getLocal() {
        return local;
    }

    public void setLocal(TorneoEquipo local) {
        this.local = local;
    }

    public Long getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(Long puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public TorneoEquipo getVisitante() {
        return visitante;
    }

    public void setVisitante(TorneoEquipo visitante) {
        this.visitante = visitante;
    }

    public Long getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(Long puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public TorneoCancha getCancha() {
        return cancha;
    }

    public void setCancha(TorneoCancha cancha) {
        this.cancha = cancha;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public StatusPartido getStatus() {
        return status;
    }

    public void setStatus(StatusPartido status) {
        this.status = status;
    }

    public Set<PartidoArbitro> getArbitros() {
        return arbitros;
    }

    public void setArbitros(Set<PartidoArbitro> arbitros) {
        this.arbitros = arbitros;
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public Set<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Set<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public Set<Bloqueo> getBloqueos() {
        return bloqueos;
    }

    public void setBloqueos(Set<Bloqueo> bloqueos) {
        this.bloqueos = bloqueos;
    }

    public Set<Enceste> getEncestes() {
        return encestes;
    }

    public void setEncestes(Set<Enceste> encestes) {
        this.encestes = encestes;
    }

    public Set<Falta> getFaltas() {
        return faltas;
    }

    public Set<Rebote> getRebotes() {
        return rebotes;
    }

    public void setRebotes(Set<Rebote> rebotes) {
        this.rebotes = rebotes;
    }

    public Set<Robo> getRobos() {
        return robos;
    }

    public void setRobos(Set<Robo> robos) {
        this.robos = robos;
    }

    public void setFaltas(Set<Falta> faltas) {
        this.faltas = faltas;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", jornada=" + jornada +
                ", local=" + local +
                ", puntosLocal=" + puntosLocal +
                ", visitante=" + visitante +
                ", puntosVisitante=" + puntosVisitante +
                ", cancha=" + cancha +
                ", horario=" + horario +
                ", status=" + status +
                '}';
    }
}
