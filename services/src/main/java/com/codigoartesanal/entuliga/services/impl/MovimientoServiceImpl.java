package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.MovimientoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Override
    public Map<String, Object> createMovimiento(Map<String, String> movimiento) {
        return null;
    }

    @Override
    public void deleteMovimiento(Long idMovimiento) {

    }

    @Override
    public List<Map<String, Object>> movimientosByPartido(Long idPartido) {
        return null;
    }
}
