package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.TorneoEquipo;
import com.codigoartesanal.entuliga.model.TorneoJugador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 25/05/15.
 */
public interface TorneoJugadorRepository extends CrudRepository<TorneoJugador, Long> {
    List<TorneoJugador> findAllByTorneoEquipo(TorneoEquipo torneoEquipo);
}
