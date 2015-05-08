package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by betuzo on 25/01/15.
 */
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
}
