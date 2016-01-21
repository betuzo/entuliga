package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.ArbitroService;
import com.codigoartesanal.entuliga.services.GeneralService;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 22/05/15.
 */
@Controller
@RequestMapping("/arbitro")
public class ArbitroController {

    @Autowired
    ArbitroService arbitroService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createArbitro(@RequestBody Map<String, String> arbitro, User user) {
        return arbitroService.createArbitro(arbitro, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{arbitro}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateArbitro(@RequestBody Map<String, String> arbitro, User user) {
        return arbitroService.createArbitro(arbitro, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listArbitroByUser(User user) {
        return arbitroService.listArbitroByAdmin(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{arbitro}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteLiga(@PathVariable("arbitro") Long idArbitro, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put(ArbitroService.PROPERTY_ID, idArbitro);
        DeleteStatusEnum result = arbitroService.deleteArbitro(idArbitro);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Arbitro eliminado");
        } else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "El arbitro no se puede eliminar, ya participa en torneo(s)");
        }
        return response;
    }
}
