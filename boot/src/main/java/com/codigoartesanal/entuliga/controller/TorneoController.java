package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.JornadaService;
import com.codigoartesanal.entuliga.services.TorneoCanchaService;
import com.codigoartesanal.entuliga.services.TorneoEquipoService;
import com.codigoartesanal.entuliga.services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    JornadaService jornadaService;

    @Autowired
    TorneoEquipoService torneoEquipoService;

    @Autowired
    TorneoCanchaService torneoCanchaService;

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
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneos() {
        return torneoService.listTorneo();
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateTorneo(@RequestBody Map<String, String> liga, User user) {
        return torneoService.createTorneo(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/equipo" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneoEquipoByTorneo(@PathVariable("torneo") Long idTorneo) {
        return torneoEquipoService.listTorneoEquipoByTorneo(idTorneo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/jornada" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listJornadaByTorneo(@PathVariable("torneo") Long idTorneo) {
        return jornadaService.listJornadaByTorneo(idTorneo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/cancha" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listCanchaByTorneo(@PathVariable("torneo") Long idTorneo) {
        return torneoCanchaService.listCanchaByTorneo(idTorneo);
    }
}
