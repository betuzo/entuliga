package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Asistencia;
import com.codigoartesanal.entuliga.model.Partido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 4/09/15.
 */
public interface AsistenciaRepository extends CrudRepository<Asistencia, Long> {
    List<Asistencia> findAllByPartido(Partido partido);
}
