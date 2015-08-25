package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Jornada;
import com.codigoartesanal.entuliga.model.Partido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 22/05/15.
 */
public interface PartidoRepository extends CrudRepository<Partido, Long> {
    List<Partido> findAllByJornada(Jornada jornada);
}
