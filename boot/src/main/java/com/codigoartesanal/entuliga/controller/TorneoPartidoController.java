package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/torneopartido")
public class TorneoPartidoController {

    @Autowired
    PartidoService partidoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneoPartido(
            @RequestBody Map<String, String> partido) {
        return partidoService.createPartido(partido);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateTorneoPartido(
            @RequestBody Map<String, String> partido) {
        return partidoService.createPartido(partido);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        Map<String, Object> response = new HashMap<>();
        response.put(PartidoService.PROPERTY_ID, idPartido);
        partidoService.deletePartido(idPartido);
        response.put(PartidoService.PROPERTY_RESULT, true);
        response.put(PartidoService.PROPERTY_MESSAGE, "Partido eliminado");
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> obtenerTorneoPartidoById(@PathVariable("torneopartido") Long idPartido) {
        Map<String, Object> response = partidoService.partidoById(idPartido);
        return response;
    }

}
