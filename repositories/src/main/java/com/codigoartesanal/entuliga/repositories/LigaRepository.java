package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 12/05/15.
 */
public interface LigaRepository extends CrudRepository<Liga, Long> {
    List<Liga> findAllByAdmin(User admin);
}
