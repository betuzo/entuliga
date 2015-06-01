package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.TorneoEquipoService;
import com.codigoartesanal.entuliga.services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by betuzo on 18/05/15.
 */
@Controller
@RequestMapping("/torneo")
public class TorneoController {

    @Autowired
    TorneoService torneoService;

    @Autowired
    TorneoEquipoService torneoEquipoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneo(@RequestBody Map<String, String> liga, User user) {
        return torneoService.createTorneo(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateTorneo(@RequestBody Map<String, String> liga, User user) {
        return torneoService.createTorneo(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/equipo" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> listTorneoByLiga(
            @PathVariable("torneo") Long idTorneo, @RequestBody Map<String, String> torneoEquipo) {
        return torneoEquipoService.createTorneoEquipo(idTorneo, torneoEquipo);
    }
}
