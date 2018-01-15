package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.services.impl.DeleteStatusEnum;

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
    public static final String PROPERTY_NOMBRE_ARBITRO      = "nombre";
    public static final String PROPERTY_MATERNO_ARBITRO      = "materno";
    public static final String PROPERTY_PATERNO_ARBITRO      = "paterno";
    public static final String PROPERTY_ARBITRO_ID      = "arbitroId";


    Map<String,Object> createPartidoArbitro(Map<String, String> partidoArbitro);
    List<Map<String,Object>> arbitrosByPartido(Long idPartido);
    DeleteStatusEnum deleteArbitroPartido (Long idArbitroPartido);


}
