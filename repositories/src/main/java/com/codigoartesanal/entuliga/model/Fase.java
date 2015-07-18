package com.codigoartesanal.entuliga.model;

/**
 * Created by betuzo on 8/05/15.
 */
public enum Fase {
    REGULAR, DIECICEISAVOS, OCTAVOS, CUARTOS, SEMIFINAL, FINAL;

    public String getDescription() {
        switch(this) {
            case REGULAR:
                return "Regular";
            case DIECICEISAVOS:
                return "Dieciceisavos";
            case OCTAVOS:
                return "Octavos";
            case CUARTOS:
                return "Cuartos";
            case SEMIFINAL:
                return "Semifinal";
            case FINAL:
                return "Final";
            default:
                return null;
        }
    }
}
