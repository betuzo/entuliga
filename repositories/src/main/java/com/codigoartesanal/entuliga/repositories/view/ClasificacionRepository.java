package com.codigoartesanal.entuliga.repositories.view;

import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.view.Clasificacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 21/10/15.
 */
public interface ClasificacionRepository extends CrudRepository<Clasificacion, Long> {
    List<Clasificacion> findAllByTorneoOrderByTotalGanadosDescDiferenciaPuntosDesc(Torneo torneo);
}
