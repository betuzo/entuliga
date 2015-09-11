package com.codigoartesanal.entuliga.services;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
public interface MovimientoService {
    public static final String PROPERTY_ID                  = "id";
    public static final String PROPERTY_PARTIDO_ID          = "partidoId";
    public static final String PROPERTY_TIEMPO_DES          = "tiempoDes";
    public static final String PROPERTY_MINUTO              = "minuto";
    public static final String PROPERTY_SEGUNDO             = "segundo";
    public static final String PROPERTY_TIPO                = "tipo";
    public static final String PROPERTY_ENTRA_ID            = "entraId";
    public static final String PROPERTY_ENTRA_NOMBRE        = "entraNombre";
    public static final String PROPERTY_SALE_ID             = "saleId";
    public static final String PROPERTY_SALE_NOMBRE         = "saleNombre";

    public static final String PROPERTY_RESULT              = "result";
    public static final String PROPERTY_MESSAGE             = "message";

    Map<String,Object> createMovimiento(Map<String, String> movimiento);

    void deleteMovimiento(Long idMovimiento);

    List<Map<String,Object>> movimientosByPartido(Long idPartido);
}
