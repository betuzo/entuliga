package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Jornada;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoEquipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by betuzo on 25/05/15.
 */
public interface TorneoEquipoRepository extends CrudRepository<TorneoEquipo, Long> {
    List<TorneoEquipo> findAllByTorneo(Torneo torneo);
    @Query("select te from TorneoEquipo te where te.torneo = :torneo and te not in " +
            "(select pa.local from Partido pa where pa.jornada = :jornada) and te not in " +
            "(select pa.visitante from Partido pa where pa.jornada = :jornada) " )
    List<TorneoEquipo> findAllByTorneoNotInJornada(@Param("torneo") Torneo torneo, @Param("jornada") Jornada jornada);
}
