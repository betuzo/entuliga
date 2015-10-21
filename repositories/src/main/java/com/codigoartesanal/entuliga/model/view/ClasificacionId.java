package com.codigoartesanal.entuliga.model.view;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.TorneoEquipo;

import java.io.Serializable;

/**
 * Created by betuzo on 21/10/15.
 */
public class ClasificacionId implements Serializable {
    TorneoEquipo equipo;
    Torneo torneo;
}
