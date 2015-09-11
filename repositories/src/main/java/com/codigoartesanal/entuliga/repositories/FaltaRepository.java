package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Falta;
import com.codigoartesanal.entuliga.model.Partido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 4/09/15.
 */
public interface FaltaRepository extends CrudRepository<Falta, Long> {
    List<Falta> findAllByPartido(Partido partido);
}
