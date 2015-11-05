package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.TipoRebote;
import com.codigoartesanal.entuliga.services.TipoReboteService;
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
@RequestMapping("/tiporebote")
public class TipoReboteController {
    @RequestMapping(
            value = { "" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<Map<String, String>> listTipoRebote() {
        List<Map<String, String>> tipoRebotes = new ArrayList<>();
        for (TipoRebote tipoRebote : TipoRebote.values()) {
            Map<String, String> tipoEncesteMap = new HashMap<>();
            tipoEncesteMap.put(TipoReboteService.PROPERTY_CLAVE, tipoRebote.toString());
            tipoEncesteMap.put(TipoReboteService.PROPERTY_DESCRIPCION, tipoRebote.getDescription());
            tipoRebotes.add(tipoEncesteMap);
        }

        return tipoRebotes;
    }
}
