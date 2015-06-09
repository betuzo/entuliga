package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
