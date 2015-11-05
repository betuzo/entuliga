package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.Movimiento;
import com.codigoartesanal.entuliga.services.FaltaService;
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
@RequestMapping("/movimiento")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createMovimiento(
            @RequestBody Map<String, String> movimiento) {
        return movimientoService.createMovimiento(movimiento);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{movimiento}" },
            method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteMovimiento(@PathVariable("movimiento") Long idMovimiento) {
        Map<String, Object> response = new HashMap<>();
        response.put(MovimientoService.PROPERTY_ID, idMovimiento);
        movimientoService.deleteMovimiento(idMovimiento);
        response.put(MovimientoService.PROPERTY_RESULT, true);
        response.put(MovimientoService.PROPERTY_MESSAGE, "Movimiento eliminada");
        return response;
    }

}
