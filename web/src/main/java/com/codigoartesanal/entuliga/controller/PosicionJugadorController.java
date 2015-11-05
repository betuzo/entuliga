package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.PosicionJugador;
import com.codigoartesanal.entuliga.services.PosicionJugadorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 4/06/15.
 */
@Controller
@RequestMapping("/posicion")
public class PosicionJugadorController {
    @RequestMapping(
            value = { "" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<Map<String, String>> listFase() {
        List<Map<String, String>> fases = new ArrayList<>();
        for (PosicionJugador posicionJugador : PosicionJugador.values()) {
            Map<String, String> faseMap = new HashMap<>();
            faseMap.put(PosicionJugadorService.PROPERTY_CLAVE, posicionJugador.toString());
            faseMap.put(PosicionJugadorService.PROPERTY_DESCRIPCION, posicionJugador.getDescription());
            fases.add(faseMap);
        }

        return fases;
    }
}
