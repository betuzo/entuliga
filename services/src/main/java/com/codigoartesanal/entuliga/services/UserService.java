package com.codigoartesanal.entuliga.services;

import java.util.Map;

/**
 * Created by betuzo on 07/04/15.
 */
public interface UserService {
    public static final String PROPERTY_ID              = "id";
    public static final String PROPERTY_USERNAME        = "username";
    public static final String PROPERTY_TOKEN           = "token";
    public static final String PROPERTY_ROLES           = "roles";

    Map<String, Object> findByUsername(String username);
}
