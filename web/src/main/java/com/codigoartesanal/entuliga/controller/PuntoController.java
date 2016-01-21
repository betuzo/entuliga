package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.EncesteService;
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
@RequestMapping("/punto")
public class PuntoController {

    @Autowired
    EncesteService encesteService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createPunto(
            @RequestBody Map<String, String> enceste) {
        return encesteService.createEnceste(enceste);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{punto}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deletePunto(@PathVariable("punto") Long idPunto) {
        Map<String, Object> response = new HashMap<>();
        response.put(EncesteService.PROPERTY_ID, idPunto);
        encesteService.deleteEnceste(idPunto);
        response.put(GeneralService.PROPERTY_RESULT, true);
        response.put(GeneralService.PROPERTY_MESSAGE, "Punto eliminado");
        return response;
    }

}
