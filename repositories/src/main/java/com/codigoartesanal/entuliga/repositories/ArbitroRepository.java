package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Arbitro;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by betuzo on 22/05/15.
 */
public interface ArbitroRepository extends CrudRepository<Arbitro, Long> {
    List<Arbitro> findAllByAdmin(User admin);

    @Query("select ar from Arbitro ar where ar not in " +
            "(select ta.arbitro from TorneoArbitro ta where ta.torneo = :torneo) " +
            "and lower(ar.nombre) like :likeName")
    List<Arbitro> findAllNotInTorneoAndNombreContaining(
            @Param("torneo") Torneo torneo, @Param("likeName") String likeName);

    @Transactional
    @Modifying
    @Query("update Arbitro e set e.rutaFoto = :rutaFotoArbitro where e.id = :id")
    int updateFotoByIdArbitro(@Param("rutaFotoArbitro") String foto, @Param("id") Long id);
}
