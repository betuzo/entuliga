package com.codigoartesanal.entuliga.services;

/**
 * Created by betuzo on 27/10/15.
 */
public enum PathPhoto {
    JUGADOR_BASE,
    EQUIPO_BASE,
    JUGADOR_DEFAULT,
    EQUIPO_DEFAULT;

    public String getPath() {
        switch(this) {
            case JUGADOR_BASE:
                return "photo/jugador/";
            case EQUIPO_BASE:
                return "photo/equipo/";
            case JUGADOR_DEFAULT:
                return "photo/jugador/jugador-default.png";
            case EQUIPO_DEFAULT:
                return "photo/equipo/equipo-default.svg";
            default:
                return null;
        }
    }
}
