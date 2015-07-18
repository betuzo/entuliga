package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.LigaService;
import com.codigoartesanal.entuliga.services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 11/05/15.
 */
@Controller
@RequestMapping("/liga")
public class LigaController {

    @Autowired
    LigaService ligaService;

    @Autowired
    TorneoService torneoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Liga createLiga(@RequestBody Map<String, String> liga, User user) {
        return ligaService.createLiga(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Liga updateLiga(@RequestBody Map<String, String> liga, User user) {
        return ligaService.createLiga(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listLigaByUser(User user) {
        return ligaService.listLigaByUser(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{liga}/torneo" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneoByLiga(@PathVariable("liga") Long idLiga) {
        return this.torneoService.listTorneoByLiga(idLiga);
    }
}
