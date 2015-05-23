package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.ArbitroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            value = { "" },
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

}
