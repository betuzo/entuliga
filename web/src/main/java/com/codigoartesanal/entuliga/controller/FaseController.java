package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.Fase;
import com.codigoartesanal.entuliga.services.FaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 25/05/15.
 */
@Controller
@RequestMapping("/fase")
public class FaseController {
    @RequestMapping(
            value = { "" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<Map<String, String>> listFase() {
        List<Map<String, String>> fases = new ArrayList<>();
        for (Fase fase : Fase.values()) {
            Map<String, String> faseMap = new HashMap<>();
            faseMap.put(FaseService.PROPERTY_CLAVE, fase.toString());
            faseMap.put(FaseService.PROPERTY_DESCRIPCION, fase.getDescription());
            fases.add(faseMap);
        }

        return fases;
    }
}
