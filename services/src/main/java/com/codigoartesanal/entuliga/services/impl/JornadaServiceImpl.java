package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.JornadaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 06/06/15.
 */
@Service
public class JornadaServiceImpl implements JornadaService {

    @Override
    public List<Map<String, Object>> listJornadaByTorneo(Long idTorneo) {
        return null;
    }

    @Override
    public Map<String, Object> createJornada(Map<String, String> jornada) {
        return null;
    }
}
