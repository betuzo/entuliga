package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.BloqueoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class BloqueoServiceImpl implements BloqueoService {
    @Override
    public Map<String, Object> createBloqueo(Map<String, String> bloqueo) {
        return null;
    }

    @Override
    public void deleteBloqueo(Long idBloqueo) {

    }

    @Override
    public List<Map<String, Object>> bloqueosByPartido(Long idPartido) {
        return null;
    }
}
