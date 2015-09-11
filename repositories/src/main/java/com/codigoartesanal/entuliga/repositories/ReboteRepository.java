package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.model.Rebote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 4/09/15.
 */
public interface ReboteRepository extends CrudRepository<Rebote, Long> {
    List<Rebote> findAllByPartido(Partido partido);
}
