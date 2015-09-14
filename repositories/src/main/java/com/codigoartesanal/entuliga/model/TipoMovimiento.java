package com.codigoartesanal.entuliga.model;

/**
 * Created by betuzo on 11/05/15.
 */
public enum TipoMovimiento {
    INICIO,SUSTITUCION,PORLESION,EXPULSION;

    public String getDescription() {
        switch(this) {
            case INICIO:
                return "Inicio";
            case SUSTITUCION:
                return "Sustitucion";
            case PORLESION:
                return "Por lesión";
            case EXPULSION:
                return "Expulsión";
            default:
                return null;
        }
    }
}
