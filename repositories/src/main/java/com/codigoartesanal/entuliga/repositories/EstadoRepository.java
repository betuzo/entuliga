package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Estado;
import com.codigoartesanal.entuliga.model.Pais;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 14/05/15.
 */
public interface EstadoRepository extends CrudRepository<Estado, Long> {
    List<Estado> findAllByPais(Pais pais);
}
