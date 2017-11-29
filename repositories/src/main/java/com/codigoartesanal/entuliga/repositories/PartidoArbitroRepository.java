package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Partido;
import com.codigoartesanal.entuliga.model.PartidoArbitro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by adan on 29/11/17.
 */
public interface PartidoArbitroRepository extends CrudRepository<PartidoArbitro, Long> {
    List<PartidoArbitro> findAllByPartido(Partido partido);

}
