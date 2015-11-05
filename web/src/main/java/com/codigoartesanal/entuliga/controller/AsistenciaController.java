package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.AsistenciaService;
import com.codigoartesanal.entuliga.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    AsistenciaService asistenciaService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createAsistencia(
            @RequestBody Map<String, String> asistencia) {
        return asistenciaService.createAsistencia(asistencia);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{asistencia}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteAsistencia(@PathVariable("asistencia") Long idAsistencia) {
        Map<String, Object> response = new HashMap<>();
        response.put(AsistenciaService.PROPERTY_ID, idAsistencia);
        asistenciaService.deleteAsistencia(idAsistencia);
        response.put(AsistenciaService.PROPERTY_RESULT, true);
        response.put(AsistenciaService.PROPERTY_MESSAGE, "Asistencia eliminada");
        return response;
    }

}
