package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.Cancha;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 11/05/15.
 */
@Controller
@RequestMapping("/cancha")
public class CanchaController {

    @Autowired
    CanchaService canchaService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Cancha createCancha(@RequestBody Map<String, String> liga, User user) {
        return canchaService.createCancha(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{cancha}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Cancha updateCancha(
            @PathVariable("cancha") Long idCancha,
            @RequestBody Map<String, String> cancha, User user) {
        return canchaService.createCancha(cancha, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listCanchaByUser(User user) {
        return canchaService.listCanchaByUser(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/search/{criterio}" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listCanchaByName(
            @PathVariable("criterio") String criterio,
            @RequestParam(value = "tipo") String tipo,
            @RequestParam(value = "idTorneo") Long idTorneo) {
        switch (tipo) {
            case "notInTorneoAndContainName":
                return canchaService.listCanchaByTorneoAndContainName(idTorneo, "%" + criterio + "%");
        }
        return null;
    }

}
