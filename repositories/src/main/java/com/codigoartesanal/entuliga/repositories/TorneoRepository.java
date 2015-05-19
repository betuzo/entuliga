package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.Torneo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 18/05/15.
 */
public interface TorneoRepository extends CrudRepository<Torneo, Long> {
    List<Torneo> findAllByLiga(Liga liga);
}
