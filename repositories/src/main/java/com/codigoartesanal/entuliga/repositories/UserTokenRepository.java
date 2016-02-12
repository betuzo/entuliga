package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.UserToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by betuzo on 10/02/16.
 */
public interface UserTokenRepository extends CrudRepository<UserToken, Long> {
}
