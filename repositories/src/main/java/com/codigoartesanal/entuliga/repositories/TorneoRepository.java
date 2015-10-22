package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 18/05/15.
 */
public interface TorneoRepository extends CrudRepository<Torneo, Long> {
    List<Torneo> findAllByLiga(Liga liga);
    Torneo findByClave(String clave);

    @Query("Select new com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO(te, e, tj, j, sum(en.valor) as valor) " +
            "from TorneoEquipo te, TorneoJugador tj, Equipo e, Jugador j, Enceste en " +
            "where tj.torneoEquipo = te " +
            "and tj = en.tirador " +
            "and te.equipo = e " +
            "and tj.jugador = j " +
            "and te.torneo = :torneo " +
            "group by te, e, tj, j " +
            "order by valor desc")
    List<EstadisticaJugadorDTO> findAllLideresPuntosByTorneo(@Param("torneo") Torneo torneo);

    @Query("Select new com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO(te, e, tj, j, count(*) as valor) " +
            "from TorneoEquipo te, TorneoJugador tj, Equipo e, Jugador j, Rebote r " +
            "where tj.torneoEquipo = te " +
            "and tj = r.jugador " +
            "and te.equipo = e " +
            "and tj.jugador = j " +
            "and te.torneo = :torneo " +
            "group by te, e, tj, j " +
            "order by valor desc")
    List<EstadisticaJugadorDTO> findAllLideresRebotesByTorneo(@Param("torneo") Torneo torneo);

    @Query("Select new com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO(te, e, tj, j, count(*) as valor) " +
            "from TorneoEquipo te, TorneoJugador tj, Equipo e, Jugador j, Asistencia a " +
            "where tj.torneoEquipo = te " +
            "and tj = a.asiste " +
            "and te.equipo = e " +
            "and tj.jugador = j " +
            "and te.torneo = :torneo " +
            "group by te, e, tj, j " +
            "order by valor desc")
    List<EstadisticaJugadorDTO> findAllLideresAsistenciasByTorneo(@Param("torneo") Torneo torneo);

    @Query("Select new com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO(te, e, tj, j, count(*) as valor) " +
            "from TorneoEquipo te, TorneoJugador tj, Equipo e, Jugador j, Bloqueo b " +
            "where tj.torneoEquipo = te " +
            "and tj = b.bloquea " +
            "and te.equipo = e " +
            "and tj.jugador = j " +
            "and te.torneo = :torneo " +
            "group by te, e, tj, j " +
            "order by valor desc")
    List<EstadisticaJugadorDTO> findAllLideresBloqueosByTorneo(@Param("torneo") Torneo torneo);

    @Query("Select new com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO(te, e, tj, j, count(*) as valor) " +
            "from TorneoEquipo te, TorneoJugador tj, Equipo e, Jugador j, Robo r " +
            "where tj.torneoEquipo = te " +
            "and tj = r.robador " +
            "and te.equipo = e " +
            "and tj.jugador = j " +
            "and te.torneo = :torneo " +
            "group by te, e, tj, j " +
            "order by valor desc")
    List<EstadisticaJugadorDTO> findAllLideresRobosByTorneo(@Param("torneo") Torneo torneo);
}
