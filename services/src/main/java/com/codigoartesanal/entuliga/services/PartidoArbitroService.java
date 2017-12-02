package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by adan on 28/11/17.
 */
public interface PartidoArbitroService {
    public static final String PROPERTY_ID                  ="id";
    public static final String PROPERTY_PARTIDO_ID          ="partidoId";
    public static final String PROPERTY_TORNEO_ARBITRO_ID   ="torneoArbitroId";
    public static final String PROPERTY_TIPO_ARBITRO        ="tipoArbitro";

    Map<String,Object> createPartidoArbitro(Map<String, String> partidoArbitro);

    List<Map<String,Object>> arbitrosByPartido(Long idPartido);



}
