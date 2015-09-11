package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.AsistenciaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Override
    public Map<String, Object> createAsistencia(Map<String, String> asistencia) {
        return null;
    }

    @Override
    public void deleteAsistencia(Long idAsistencia) {

    }

    @Override
    public List<Map<String, Object>> asistenciasByPartido(Long idPartido) {
        return null;
    }
}
