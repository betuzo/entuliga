package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Jornada;
import com.codigoartesanal.entuliga.model.Partido;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by betuzo on 22/05/15.
 */
public interface PartidoRepository extends CrudRepository<Partido, Long> {
    List<Partido> findAllByJornada(Jornada jornada);

    @Modifying
    @Query("update Partido pt set pt.puntosLocal = pt.puntosLocal + :puntosLocal where pt.id = :id")
    int acumulaPuntosLocal(@Param("puntosLocal") int puntosLocal, @Param("id") Long id);

    @Modifying
    @Query("update Partido pt set pt.puntosVisitante = pt.puntosVisitante + :puntosVisitante where pt.id = :id")
    int acumulaPuntosVisita(@Param("puntosVisitante") int puntosVisitante, @Param("id") Long id);

    @Modifying
    @Query("update Partido pt set pt.puntosLocal = pt.puntosLocal - :puntosLocal where pt.id = :id")
    int reducePuntosLocal(@Param("puntosLocal") int puntosLocal, @Param("id") Long id);

    @Modifying
    @Query("update Partido pt set pt.puntosVisitante = pt.puntosVisitante - :puntosVisitante where pt.id = :id")
    int reducePuntosVisita(@Param("puntosVisitante") int puntosVisitante, @Param("id") Long id);
}
