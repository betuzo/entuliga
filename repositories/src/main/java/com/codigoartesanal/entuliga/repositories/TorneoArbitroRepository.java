package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoArbitro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 17/06/16.
 */
public interface TorneoArbitroRepository extends CrudRepository<TorneoArbitro, Long> {
    List<TorneoArbitro> findAllByTorneo(Torneo torneo);
}
