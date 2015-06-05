package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.TorneoJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/torneojugador")
public class TorneoJugadorController {

    @Autowired
    TorneoJugadorService torneoJugadorService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneoJugador(
            @RequestBody Map<String, String> torneoJugador) {
        return torneoJugadorService.createTorneoJugador(torneoJugador);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneojugador}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteTorneoEquipo(@PathVariable("torneojugador") Long idTorneoJugador) {
        Map<String, Object> response = new HashMap<>();
        response.put(TorneoJugadorService.PROPERTY_ID, idTorneoJugador);
        torneoJugadorService.deleteTorneoJugador(idTorneoJugador);
        response.put(TorneoJugadorService.PROPERTY_RESULT, true);
        response.put(TorneoJugadorService.PROPERTY_MESSAGE, "Jugador eliminado");
        return response;
    }

}
