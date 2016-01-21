package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.GeneralService;
import com.codigoartesanal.entuliga.services.LigaService;
import com.codigoartesanal.entuliga.services.TorneoService;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 11/05/15.
 */
@Controller
@RequestMapping("/liga")
public class LigaController {

    @Autowired
    LigaService ligaService;

    @Autowired
    TorneoService torneoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createLiga(@RequestBody Map<String, String> liga, User user) {
        return ligaService.createLiga(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{liga}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateLiga(@RequestBody Map<String, String> liga, User user) {
        return ligaService.createLiga(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{liga}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteLiga(@PathVariable("liga") Long idLiga, User user) {
        Map<String, Object> result = new HashMap<>();
        result.put(LigaService.PROPERTY_ID, idLiga);
        DeleteStatusEnum resultDelete = ligaService.deleteLiga(idLiga);
        if (resultDelete == DeleteStatusEnum.OK) {
            result.put(GeneralService.PROPERTY_RESULT, true);
            result.put(GeneralService.PROPERTY_MESSAGE, "Liga eliminada");
        } else {
            result.put(GeneralService.PROPERTY_RESULT, false);
            result.put(GeneralService.PROPERTY_MESSAGE,
                    "La liga no se puede eliminar, ya tiene torneo(s) programado(s)");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listLigaByUser(User user) {
        return ligaService.listLigaByUser(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{liga}/torneo" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneoByLiga(@PathVariable("liga") Long idLiga) {
        return this.torneoService.listTorneoByLiga(idLiga);
    }
}
