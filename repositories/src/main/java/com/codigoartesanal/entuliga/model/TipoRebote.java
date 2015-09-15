package com.codigoartesanal.entuliga.model;

/**
 * Created by betuzo on 11/05/15.
 */
public enum TipoRebote {
    OFENSIVO,DEFENSIVO;

    public String getDescription() {
        switch(this) {
            case DEFENSIVO:
                return "Defensivo";
            case OFENSIVO:
                return "Ofensivo";
            default:
                return null;
        }
    }
}
