package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO;
import com.codigoartesanal.entuliga.services.*;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 18/05/15.
 */
@Controller
@RequestMapping("/torneo")
public class TorneoController {

    @Autowired
    TorneoService torneoService;

    @Autowired
    JornadaService jornadaService;

    @Autowired
    TorneoEquipoService torneoEquipoService;

    @Autowired
    TorneoCanchaService torneoCanchaService;

    @Autowired
    TorneoArbitroService torneoArbitroService;

    @Autowired
    ClasificacionService clasificacionService;

    @Autowired
    EstadisticaService estadisticaService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createTorneo(@RequestBody Map<String, String> liga, User user) {
        return torneoService.createTorneo(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneos() {
        return torneoService.listTorneo();
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateTorneo(@RequestBody Map<String, String> liga, User user) {
        return torneoService.createTorneo(liga, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteTorneo(@PathVariable("torneo") Long idTorneo, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put(TorneoService.PROPERTY_ID, idTorneo);
        DeleteStatusEnum result = torneoService.deleteTorneo(idTorneo);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Torneo eliminado");
        } else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "El torneo no se puede eliminar, ya tiene participantes");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> listTorneo(@PathVariable("torneo") String clave) {
        return torneoService.listTorneoByClave(clave);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/equipo" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listTorneoEquipoByTorneo(@PathVariable("torneo") Long idTorneo) {
        return torneoEquipoService.listTorneoEquipoByTorneo(idTorneo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/jornada" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listJornadaByTorneo(@PathVariable("torneo") Long idTorneo) {
        return jornadaService.listJornadaByTorneo(idTorneo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/cancha" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listCanchaByTorneo(@PathVariable("torneo") Long idTorneo) {
        return torneoCanchaService.listCanchaByTorneo(idTorneo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/arbitro" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listArbitroByTorneo(@PathVariable("torneo") Long idTorneo) {
        return torneoArbitroService.listArbitroByTorneo(idTorneo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/clasificacion" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listClasificacionByTorneo(@PathVariable("torneo") Long idTorneo) {
        return clasificacionService.listClasificacionByTorneo(idTorneo);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{torneo}/estadistica" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<EstadisticaJugadorDTO> listEstadisticaByTorneoTopFive(@PathVariable("torneo") Long idTorneo) {
        return estadisticaService.listLideresTodosByTorneoTopFive(idTorneo);
    }
}
