package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 22/05/15.
 */
@Controller
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    JugadorService jugadorService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createJugador(@RequestBody Map<String, String> jugador, User user) {
        return jugadorService.createJugador(jugador, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{jugador}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateJugador(@RequestBody Map<String, String> jugador, User user) {
        return jugadorService.createJugador(jugador, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listJugadorByUser(User user) {
        return jugadorService.listJugadorByAdmin(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/search/{criterio}" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listEquipoByName(
            @PathVariable("criterio") String criterio,
            @RequestParam(value = "tipo") String tipo,
            @RequestParam(value = "idTorneo") Long idTorneo) {
        switch (tipo) {
            case "notInTorneoAndContainName":
                return jugadorService.listJugadorByTorneoAndContainName(idTorneo, "%" + criterio + "%");
        }
        return null;
    }
}
