package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.FaltaService;
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
@RequestMapping("/falta")
public class FaltaController {

    @Autowired
    FaltaService faltaService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createFalta(
            @RequestBody Map<String, String> falta) {
        return faltaService.createFalta(falta);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{falta}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteFalta(@PathVariable("falta") Long idFalta) {
        Map<String, Object> response = new HashMap<>();
        response.put(FaltaService.PROPERTY_ID, idFalta);
        faltaService.deleteFalta(idFalta);
        response.put(GeneralService.PROPERTY_RESULT, true);
        response.put(GeneralService.PROPERTY_MESSAGE, "Falta eliminada");
        return response;
    }

}
