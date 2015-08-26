package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Cancha;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by betuzo on 25/08/15.
 */
public interface CanchaRepository extends CrudRepository<Cancha, Long> {
    List<Cancha> findAllByAdmin(User admin);

    @Query("select ca from Cancha ca where ca not in " +
            "(select tc.cancha from TorneoCancha tc where tc.torneo = :torneo) " +
            "and ca.nombre like :likeName")
    List<Cancha> findAllNotInTorneoAndNombreContaining(
            @Param("torneo") Torneo torneo,@Param("likeName") String likeName);
}
