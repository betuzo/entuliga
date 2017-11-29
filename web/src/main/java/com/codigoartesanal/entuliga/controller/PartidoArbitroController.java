package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.PartidoArbitroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by adan on 28/11/17.
 */
@Controller
@RequestMapping("/partidoarbitro")
public class PartidoArbitroController {

    @Autowired
    PartidoArbitroService partidoArbitroService;

    @ResponseBody
    @RequestMapping(value = {""}, method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createPartidoArbitro(@RequestBody Map<String,String> partidoArbitro){
        System.out.println("Controller create Partido Arbitro");
        return partidoArbitroService.createPartidoArbitro(partidoArbitro);
    }



}
