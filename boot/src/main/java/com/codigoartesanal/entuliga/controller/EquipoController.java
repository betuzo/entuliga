package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 21/05/15.
 */
@Controller
@RequestMapping("/equipo")
public class EquipoController {

    @Autowired
    EquipoService equipoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createEquipo(@RequestBody Map<String, String> equipo, User user) {
        return equipoService.createEquipo(equipo, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{equipo}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateEquipo(@RequestBody Map<String, String> equipo, User user) {
        return equipoService.createEquipo(equipo, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listEquipoByUser(User user) {
        return equipoService.listEquipoByAdmin(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/search/{criterio}" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listEquipoByName(
            @PathVariable("criterio") String criterio,
            @RequestParam(value = "tipo") String tipo,
            @RequestParam(value = "idTorneo") Long idTorneo) {
        switch (tipo) {
            case "notInTorneoAndContainName":
                return equipoService.listEquipoByTorneoAndContainName(idTorneo, "%" + criterio + "%");
        }
        return null;
    }
}
