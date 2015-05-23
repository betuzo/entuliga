package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Arbitro;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 22/05/15.
 */
public interface ArbitroRepository extends CrudRepository<Arbitro, Long> {
    List<Arbitro> findAllByAdmin(User admin);
}
