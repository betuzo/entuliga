package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.model.Robo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 4/09/15.
 */
public interface RoboRepository extends CrudRepository<Robo, Long> {
    List<Robo> findAllByPartido(Partido partido);
}
