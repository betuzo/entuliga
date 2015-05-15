package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Colonia;
import com.codigoartesanal.entuliga.model.Municipio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 14/05/15.
 */
public interface ColoniaRepository extends CrudRepository<Colonia, Long> {
    List<Colonia> findAllByMunicipio(Municipio municipio);
}
