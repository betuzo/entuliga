package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Estado;
import com.codigoartesanal.entuliga.model.Municipio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 14/05/15.
 */
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {
    List<Municipio> findAllByEstado(Estado estado);
}
