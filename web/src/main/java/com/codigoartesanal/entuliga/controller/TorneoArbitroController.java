package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.GeneralService;
import com.codigoartesanal.entuliga.services.TorneoArbitroService;
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
@RequestMapping("/torneoarbitro")
public class TorneoArbitroController {

    @Autowired
    TorneoArbitroService torneoArbitroService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneoEquipoByTorneo(
            @RequestBody Map<String, String> torneoArbitro) {
        return torneoArbitroService.createTorneoArbitro(torneoArbitro);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneoarbitro}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteTorneoEquipo(@PathVariable("torneoarbitro") Long idTorneoArbitro) {
        Map<String, Object> response = new HashMap<>();
        response.put(TorneoEquipoService.PROPERTY_ID, idTorneoArbitro);
        DeleteStatusEnum result = torneoArbitroService.deleteTorneoArbitro(idTorneoArbitro);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Arbitro eliminado");
        } else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "El arbitro no se puede eliminar, ya tiene participación en un partido");
        }
        return response;
    }

}
