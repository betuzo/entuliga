package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.OrigenEstadistica;

/**
 * Created by betuzo on 16/01/16.
 */
public interface PathWeb {

    String getValidPathWebFoto(String path);

    String getValidPathWebLogo(String path, OrigenEstadistica origenEstadistica);
}
