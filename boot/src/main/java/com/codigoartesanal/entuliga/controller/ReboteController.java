package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.BloqueoService;
import com.codigoartesanal.entuliga.services.ReboteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/rebote")
public class ReboteController {

    @Autowired
    ReboteService reboteService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createRebote(
            @RequestBody Map<String, String> rebote) {
        return reboteService.createRebote(rebote);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{rebote}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteRebote(@PathVariable("rebote") Long idRebote) {
        Map<String, Object> response = new HashMap<>();
        response.put(ReboteService.PROPERTY_ID, idRebote);
        reboteService.deleteRebote(idRebote);
        response.put(ReboteService.PROPERTY_RESULT, true);
        response.put(ReboteService.PROPERTY_MESSAGE, "Rebote eliminada");
        return response;
    }

}
