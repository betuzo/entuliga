package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.*;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 3/06/15.
 */
@Controller
@RequestMapping("/torneopartido")
public class TorneoPartidoController {

    @Autowired
    PartidoService partidoService;

    @Autowired
    EncesteService encesteService;

    @Autowired
    FaltaService faltaService;

    @Autowired
    MovimientoService movimientoService;

    @Autowired
    AsistenciaService asistenciaService;

    @Autowired
    BloqueoService bloqueoService;

    @Autowired
    ReboteService reboteService;

    @Autowired
    RoboService roboService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneoPartido(
            @RequestBody Map<String, String> partido) {
        return partidoService.createPartido(partido);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateTorneoPartido(
            @RequestBody Map<String, String> partido) {
        return partidoService.createPartido(partido);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        Map<String, Object> response = new HashMap<>();
        response.put(PartidoService.PROPERTY_ID, idPartido);
        DeleteStatusEnum result = partidoService.deletePartido(idPartido);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Partido eliminado");
        }else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "El partido no se puede eliminar, ya tiene estadisticas registradas");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> obtenerTorneoPartidoById(@PathVariable("torneopartido") Long idPartido) {
        Map<String, Object> response = partidoService.partidoById(idPartido);
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}/punto" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> obtenerPuntosByTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        List<Map<String, Object>> response = encesteService.puntosByPartido(idPartido);
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}/falta" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> obtenerFaltasByTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        List<Map<String, Object>> response = faltaService.faltasByPartido(idPartido);
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}/movimiento" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> obtenerMovimientosByTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        List<Map<String, Object>> response = movimientoService.movimientosByPartido(idPartido);
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}/asistencia" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> obtenerAsistenciasByTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        List<Map<String, Object>> response = asistenciaService.asistenciasByPartido(idPartido);
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}/bloqueo" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> obtenerBloqueosByTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        List<Map<String, Object>> response = bloqueoService.bloqueosByPartido(idPartido);
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}/rebote" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> obtenerRebotesByTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        List<Map<String, Object>> response = reboteService.rebotesByPartido(idPartido);
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneopartido}/robo" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> obtenerRobosByTorneoPartido(@PathVariable("torneopartido") Long idPartido) {
        List<Map<String, Object>> response = roboService.robosByPartido(idPartido);
        return response;
    }



}
