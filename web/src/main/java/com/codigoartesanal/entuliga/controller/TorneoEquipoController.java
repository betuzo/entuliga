package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.GeneralService;
import com.codigoartesanal.entuliga.services.TorneoEquipoService;
import com.codigoartesanal.entuliga.services.TorneoJugadorService;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/torneoequipo")
public class TorneoEquipoController {

    @Autowired
    TorneoEquipoService torneoEquipoService;

    @Autowired
    TorneoJugadorService torneoJugadorService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneoEquipoByTorneo(
            @RequestBody Map<String, String> torneoEquipo) {
        return torneoEquipoService.createTorneoEquipo(torneoEquipo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneoequipo}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteTorneoEquipo(@PathVariable("torneoequipo") Long idTorneoEquipo) {
        Map<String, Object> response = new HashMap<>();
        response.put(TorneoEquipoService.PROPERTY_ID, idTorneoEquipo);
        DeleteStatusEnum result = torneoEquipoService.deleteTorneoEquipo(idTorneoEquipo);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Equipo eliminado");
        } else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "El equipo no se puede eliminar, ya tiene participación en un partido");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneoequipo}/jugador" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneoEquipoByLiga(
            @PathVariable("torneoequipo") Long idTorneoEquipo) {
        return torneoJugadorService.listTorneoJugadorByTorneoEquipo(idTorneoEquipo);
    }
}
