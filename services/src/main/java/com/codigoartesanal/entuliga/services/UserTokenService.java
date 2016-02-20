package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.TipoToken;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.model.UserToken;

import java.util.Map;

/**
 * Created by betuzo on 18/02/16.
 */
public interface UserTokenService {
    int PROPERTY_TOKEN_VIGENCIA_DAYS    = 2;

    String PROPERTY_TOKEN               = "token";
    String PROPERTY_TIPO                = "tipo";
    String PROPERTY_FECHA_VIGENCIA      = "fechaVigencia";
    String PROPERTY_USERNAME            = "username";

    Map<String,Object> userTokenById(String token);

    UserToken createUserTokenByUserAndTipo(User user, TipoToken tipo);
}
