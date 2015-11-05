package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.TipoMovimiento;
import com.codigoartesanal.entuliga.services.TipoMovimientoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 25/05/15.
 */
@Controller
@RequestMapping("/tipomovimiento")
public class TipoMovimientoController {
    @RequestMapping(
            value = { "" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<Map<String, String>> listTipoMovimiento() {
        List<Map<String, String>> tipoMovimientos = new ArrayList<>();
        for (TipoMovimiento tipoMovimiento : TipoMovimiento.values()) {
            Map<String, String> tipoEncesteMap = new HashMap<>();
            tipoEncesteMap.put(TipoMovimientoService.PROPERTY_CLAVE, tipoMovimiento.toString());
            tipoEncesteMap.put(TipoMovimientoService.PROPERTY_DESCRIPCION, tipoMovimiento.getDescription());
            tipoMovimientos.add(tipoEncesteMap);
        }

        return tipoMovimientos;
    }
}
