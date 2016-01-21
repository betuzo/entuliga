package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.AsistenciaService;
import com.codigoartesanal.entuliga.services.BloqueoService;
import com.codigoartesanal.entuliga.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/bloqueo")
public class BloqueoController {

    @Autowired
    BloqueoService bloqueoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createBloqueo(
            @RequestBody Map<String, String> bloqueo) {
        return bloqueoService.createBloqueo(bloqueo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{bloqueo}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteBloqueo(@PathVariable("bloqueo") Long idBloqueo) {
        Map<String, Object> response = new HashMap<>();
        response.put(BloqueoService.PROPERTY_ID, idBloqueo);
        bloqueoService.deleteBloqueo(idBloqueo);
        response.put(GeneralService.PROPERTY_RESULT, true);
        response.put(GeneralService.PROPERTY_MESSAGE, "Bloqueo eliminada");
        return response;
    }

}
