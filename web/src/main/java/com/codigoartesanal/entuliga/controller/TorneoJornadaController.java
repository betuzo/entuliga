package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.JornadaService;
import com.codigoartesanal.entuliga.services.PartidoService;
import com.codigoartesanal.entuliga.services.TorneoEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 5/06/15.
 */
@Controller
@RequestMapping("/torneojornada")
public class TorneoJornadaController {

    @Autowired
    JornadaService jornadaService;

    @Autowired
    PartidoService partidoService;

    @Autowired
    TorneoEquipoService torneoEquipoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createJornada(
            @RequestBody Map<String, String> jornada) {
        return jornadaService.createJornada(jornada);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{jornada}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteTorneoEquipo(@PathVariable("jornada") Long jornada) {
        Map<String, Object> response = new HashMap<>();
        response.put(JornadaService.PROPERTY_ID, jornada);
        jornadaService.deleteJornada(Long.valueOf(jornada));
        response.put(JornadaService.PROPERTY_RESULT, true);
        response.put(JornadaService.PROPERTY_MESSAGE, "Jornada eliminado");
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{jornada}/partido" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneoEquipoByLiga(
            @PathVariable("jornada") Long idJornada) {
        return partidoService.listPartidoByJornada(idJornada);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{jornada}/equipo" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneoEquipoByJornada(
            @PathVariable("jornada") Long idJornada) {
        return torneoEquipoService.listTorneoEquipoByJornada(idJornada);
    }
}