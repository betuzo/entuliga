package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Movimiento;
import com.codigoartesanal.entuliga.model.Partido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 4/09/15.
 */
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {
    List<Movimiento> findAllByPartido(Partido partido);
}
