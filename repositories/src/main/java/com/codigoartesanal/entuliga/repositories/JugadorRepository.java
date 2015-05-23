package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.Jugador;
import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by betuzo on 22/05/15.
 */
public interface JugadorRepository extends CrudRepository<Jugador, Long> {
    List<Jugador> findAllByAdmin(User admin);
}
