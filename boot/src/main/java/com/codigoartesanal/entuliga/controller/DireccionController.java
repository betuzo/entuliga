package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.ColoniaService;
import com.codigoartesanal.entuliga.services.EstadoService;
import com.codigoartesanal.entuliga.services.MunicipioService;
import com.codigoartesanal.entuliga.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 14/05/15.
 */
@Controller
@RequestMapping("")
public class DireccionController {

    @Autowired
    PaisService paisService;

    @Autowired
    EstadoService estadoService;

    @Autowired
    MunicipioService municipioService;

    @Autowired
    ColoniaService coloniaService;

    @ResponseBody
    @RequestMapping(
            value = { "/pais" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listPais() {
        return paisService.listPais();
    }

    @ResponseBody
    @RequestMapping(
            value = { "/pais/{pais}/estado" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listEstadoByPais(@PathVariable(value = "pais") Long idPais) {
        return estadoService.listEstadoByPais(idPais);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/estado/{estado}/municipio" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listMunicipioByEstado(@PathVariable(value = "estado") Long idEstado) {
        return municipioService.listMunicipioByEstado(idEstado);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/municipio/{municipio}/colonia" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listColoniaByMunicipio(@PathVariable(value = "municipio") Long idMunicipio) {
        return coloniaService.listColoniaByMunicipio(idMunicipio);
    }
}
