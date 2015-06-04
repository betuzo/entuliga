package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.TorneoEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/torneoequipo")
public class TorneoEquipoController {

    @Autowired
    TorneoEquipoService torneoEquipoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneoEquipoByTorneo(
            @RequestBody Map<String, String> torneoEquipo) {
        return torneoEquipoService.createTorneoEquipo(torneoEquipo);
    }
}
