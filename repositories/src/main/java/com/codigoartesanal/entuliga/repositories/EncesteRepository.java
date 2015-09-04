package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Enceste;
import com.codigoartesanal.entuliga.model.Partido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 4/09/15.
 */
public interface EncesteRepository extends CrudRepository<Enceste, Long> {
    List<Enceste> findAllByPartido(Partido partido);
}
