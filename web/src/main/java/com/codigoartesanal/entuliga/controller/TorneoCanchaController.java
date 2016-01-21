package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.GeneralService;
import com.codigoartesanal.entuliga.services.TorneoCanchaService;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 5/06/15.
 */
@Controller
@RequestMapping("/torneocancha")
public class TorneoCanchaController {

    @Autowired
    TorneoCanchaService torneoCanchaService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneoCancha(
            @RequestBody Map<String, String> torneoJornada) {
        return torneoCanchaService.createTorneoCancha(torneoJornada);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneocancha}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteTorneoEquipo(@PathVariable("torneocancha") Long idTorneoCancha) {
        Map<String, Object> response = new HashMap<>();
        response.put(TorneoCanchaService.PROPERTY_ID, idTorneoCancha);
        DeleteStatusEnum result = torneoCanchaService.deleteTorneoCancha(idTorneoCancha);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Cancha eliminado");
        } else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "La cancha no se puede eliminar, ya tiene participación en un partido");
        }
        return response;
    }
}
