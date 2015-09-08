package com.codigoartesanal.entuliga.model;

/**
 * Created by betuzo on 20/05/15.
 */
public enum StatusJugador {
    INSCRITO,ELIMINADO,DESCALIFICADO;

    public String getDescription() {
        switch(this) {
            case INSCRITO:
                return "Inscrito";
            case ELIMINADO:
                return "Eliminado";
            case DESCALIFICADO:
                return "Descalificado";
            default:
                return null;
        }
    }
}
