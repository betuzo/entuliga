package com.codigoartesanal.entuliga.model;

/**
 * Created by betuzo on 11/05/15.
 */
public enum TipoEnceste {
    TRES, DOS, TIROLIBRE, TIROCASTIGO;

    public String getDescription() {
        switch(this) {
            case TRES:
                return "Tres";
            case DOS:
                return "Dos";
            case TIROLIBRE:
                return "Tiro Libre";
            case TIROCASTIGO:
                return "Tiro Castigo";
            default:
                return null;
        }
    }

    public int getValor() {
        switch(this) {
            case TRES:
                return 3;
            case DOS:
                return 2;
            case TIROLIBRE:
                return 2;
            case TIROCASTIGO:
                return 1;
            default:
                return 0;
        }
    }
}
