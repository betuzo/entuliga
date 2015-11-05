package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.TipoFalta;
import com.codigoartesanal.entuliga.services.TipoFaltaService;
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
@RequestMapping("/tipofalta")
public class TipoFaltaController {
    @RequestMapping(
            value = { "" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<Map<String, String>> listTipoFalta() {
        List<Map<String, String>> tipoFaltas = new ArrayList<>();
        for (TipoFalta tipoFalta : TipoFalta.values()) {
            Map<String, String> tipoEncesteMap = new HashMap<>();
            tipoEncesteMap.put(TipoFaltaService.PROPERTY_CLAVE, tipoFalta.toString());
            tipoEncesteMap.put(TipoFaltaService.PROPERTY_DESCRIPCION, tipoFalta.getDescription());
            tipoFaltas.add(tipoEncesteMap);
        }

        return tipoFaltas;
    }
}
