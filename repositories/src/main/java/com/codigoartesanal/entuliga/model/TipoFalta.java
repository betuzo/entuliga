package com.codigoartesanal.entuliga.model;

/**
 * Created by betuzo on 11/05/15.
 */
public enum TipoFalta {
    DEFENSIVA,OFENSIVA;

    public String getDescription() {
        switch(this) {
            case DEFENSIVA:
                return "Defensiva";
            case OFENSIVA:
                return "Ofensiva";
            default:
                return null;
        }
    }
}
