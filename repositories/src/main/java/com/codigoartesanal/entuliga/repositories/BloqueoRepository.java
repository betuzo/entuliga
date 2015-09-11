package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Bloqueo;
import com.codigoartesanal.entuliga.model.Partido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 4/09/15.
 */
public interface BloqueoRepository extends CrudRepository<Bloqueo, Long> {
    List<Bloqueo> findAllByPartido(Partido partido);
}
