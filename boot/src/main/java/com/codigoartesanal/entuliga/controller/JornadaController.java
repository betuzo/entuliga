package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 5/06/15.
 */
@Controller
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    JornadaService jornadaService;

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
    public Map<String, Object> deleteTorneoEquipo(@PathVariable("jornada") Long idJornada) {
        Map<String, Object> response = new HashMap<>();
        response.put(JornadaService.PROPERTY_ID, idJornada);
        jornadaService.deleteJornada(idJornada);
        response.put(JornadaService.PROPERTY_RESULT, true);
        response.put(JornadaService.PROPERTY_MESSAGE, "Jornada eliminado");
        return response;
    }
}
