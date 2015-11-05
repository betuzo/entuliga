package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.TipoEnceste;
import com.codigoartesanal.entuliga.services.TipoEncesteService;
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
@RequestMapping("/tipoenceste")
public class TipoEncesteController {
    @RequestMapping(
            value = { "" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<Map<String, String>> listTipoEnceste() {
        List<Map<String, String>> tipoEncestes = new ArrayList<>();
        for (TipoEnceste tipoEnceste : TipoEnceste.values()) {
            Map<String, String> tipoEncesteMap = new HashMap<>();
            tipoEncesteMap.put(TipoEncesteService.PROPERTY_CLAVE, tipoEnceste.toString());
            tipoEncesteMap.put(TipoEncesteService.PROPERTY_DESCRIPCION, tipoEnceste.getDescription());
            tipoEncestes.add(tipoEncesteMap);
        }

        return tipoEncestes;
    }
}
