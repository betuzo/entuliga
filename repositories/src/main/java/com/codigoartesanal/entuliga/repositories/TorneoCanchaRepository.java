package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoCancha;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 25/08/15.
 */
public interface TorneoCanchaRepository extends CrudRepository<TorneoCancha, Long> {
    List<TorneoCancha> findAllByTorneo(Torneo torneo);
}
