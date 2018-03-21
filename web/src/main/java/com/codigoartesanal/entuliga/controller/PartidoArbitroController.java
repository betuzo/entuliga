package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.GeneralService;
import com.codigoartesanal.entuliga.services.PartidoArbitroService;
import com.codigoartesanal.entuliga.services.PartidoService;
import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        return partidoArbitroService.createPartidoArbitro(partidoArbitro);
    }


    @ResponseBody
    @RequestMapping(
            value = {"/{arbitroPartido}"},
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteArbitroPartido(@PathVariable("arbitroPartido") Long idArbitroPartido, User user){
        System.out.println("Delete arbitro del pardido");
        Map<String, Object> response = new HashMap<>();
        response.put(PartidoService.PROPERTY_ID, idArbitroPartido);
        DeleteStatusEnum result = partidoArbitroService.deleteArbitroPartido(idArbitroPartido);
        if (result == DeleteStatusEnum.OK){
          response.put(GeneralService.PROPERTY_RESULT, true);
          response.put(GeneralService.PROPERTY_MESSAGE, "Arbitro eliminado del partido");
        }else{
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE, "El arbitro del partido no se puede eliminar");

        }
        return response;
    }




}
