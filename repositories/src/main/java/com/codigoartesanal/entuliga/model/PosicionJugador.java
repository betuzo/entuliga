package com.codigoartesanal.entuliga.model;

/**
 * Created by betuzo on 20/05/15.
 */
public enum PosicionJugador {
    BASE,ESCOLTA,ALERO,ALAPIVOTE,PIVOTE;

    public String getDescription() {
        switch(this) {
            case BASE:
                return "Base";
            case ESCOLTA:
                return "Escolta";
            case ALERO:
                return "Alero";
            case ALAPIVOTE:
                return "Ala-Pivot";
            case PIVOTE:
                return "Pivot";
            default:
                return null;
        }
    }
}
