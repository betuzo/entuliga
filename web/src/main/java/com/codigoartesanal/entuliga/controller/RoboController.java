package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.GeneralService;
import com.codigoartesanal.entuliga.services.RoboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/robo")
public class RoboController {

    @Autowired
    RoboService roboService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createRobo(
            @RequestBody Map<String, String> robo) {
        return roboService.createRobo(robo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{robo}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteRobo(@PathVariable("robo") Long idRobo) {
        Map<String, Object> response = new HashMap<>();
        response.put(RoboService.PROPERTY_ID, idRobo);
        roboService.deleteRobo(idRobo);
        response.put(GeneralService.PROPERTY_RESULT, true);
        response.put(GeneralService.PROPERTY_MESSAGE, "Robo eliminada");
        return response;
    }

}
