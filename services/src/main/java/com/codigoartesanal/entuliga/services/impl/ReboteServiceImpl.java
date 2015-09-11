package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.ReboteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class ReboteServiceImpl implements ReboteService {
    @Override
    public Map<String, Object> createRebote(Map<String, String> rebote) {
        return null;
    }

    @Override
    public void deleteRebote(Long idRebote) {

    }

    @Override
    public List<Map<String, Object>> rebotesByPartido(Long idPartido) {
        return null;
    }
}
