package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoEquipo;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by betuzo on 21/05/15.
 */
public interface EquipoRepository extends CrudRepository<Equipo, Long>{
    List<Equipo> findAllByAdmin(User admin);

    @Query("select eq from Equipo eq where eq not in " +
            "(select te.equipo from TorneoEquipo te where te.torneo = :torneo) " +
            "and lower(eq.nombre) like :likeName")
    List<Equipo> findAllNotInTorneoAndNombreContaining(
            @Param("torneo") Torneo torneo,@Param("likeName") String likeName);

    @Transactional
    @Modifying
    @Query("update Equipo e set e.rutaLogoEquipo = :rutaLogoEquipo where e.id = :id")
    int updateLogoByIdEquipo(@Param("rutaLogoEquipo") String rutaLogoEquipo, @Param("id") Long id);
}
