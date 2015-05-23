package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 21/05/15.
 */
public interface EquipoRepository extends CrudRepository<Equipo, Long>{
    List<Equipo> findAllByAdmin(User admin);

    List<Equipo> findAllByNombreContaining(String likeName);
}
