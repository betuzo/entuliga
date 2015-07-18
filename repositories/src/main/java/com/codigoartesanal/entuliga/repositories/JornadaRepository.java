package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Jornada;
import com.codigoartesanal.entuliga.model.Torneo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 9/06/15.
 */
public interface JornadaRepository extends CrudRepository<Jornada, Long> {
    List<Jornada> findAllByTorneo(Torneo torneo);
}
