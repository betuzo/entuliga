package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Jugador;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by betuzo on 22/05/15.
 */
public interface JugadorRepository extends CrudRepository<Jugador, Long> {
    List<Jugador> findAllByAdmin(User admin);

    @Query("select ju from Jugador ju where ju not in " +
            "(select tj.jugador from TorneoJugador tj where tj.torneoEquipo.torneo = :torneo) " +
            "and lower(ju.nombre) like :likeName")
    List<Jugador> findAllNotInTorneoAndNombreContaining(
            @Param("torneo") Torneo torneo, @Param("likeName") String likeName);

    @Transactional
    @Modifying
    @Query("update Jugador e set e.rutaFoto = :rutaFotoJugador where e.id = :id")
    int updateFotoByIdJugador(@Param("rutaFotoJugador") String rutaFotoJugador, @Param("id") Long id);
}
