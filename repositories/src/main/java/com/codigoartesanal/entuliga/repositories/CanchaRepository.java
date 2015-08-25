package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Cancha;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 25/08/15.
 */
public interface CanchaRepository extends CrudRepository<Cancha, Long> {
    List<Cancha> findAllByAdmin(User admin);
}
