package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.MovimientoRepository;
import com.codigoartesanal.entuliga.repositories.PartidoRepository;
import com.codigoartesanal.entuliga.repositories.TorneoJugadorRepository;
import com.codigoartesanal.entuliga.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 10/09/15.
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    TorneoJugadorRepository torneoJugadorRepository;

    @Override
    public Map<String, Object> createMovimiento(Map<String, String> movimientoMap) {
        Movimiento movimiento = convertMapToMovimiento(movimientoMap);
        movimiento = movimientoRepository.save(movimiento);
        movimiento = populateMovimiento(movimiento);
        return convertMovimientoToMap(movimiento);
    }

    @Override
    public void deleteMovimiento(Long idMovimiento) {
        movimientoRepository.delete(idMovimiento);
    }

    @Override
    public List<Map<String, Object>> movimientosByPartido(Long idPartido) {
        return null;
    }

    private Movimiento populateMovimiento(Movimiento movimiento){
        movimiento.setPartido(partidoRepository.findOne(movimiento.getPartido().getId()));
        movimiento.setEntra(torneoJugadorRepository.findOne(movimiento.getEntra().getId()));
        if (movimiento.getSale() != null) {
            movimiento.setSale(torneoJugadorRepository.findOne(movimiento.getSale().getId()));
        }

        return movimiento;
    }

    private Movimiento convertMapToMovimiento(Map<String, String> movimientoMap) {
        Movimiento movimiento = new Movimiento();
        Partido partido = new Partido();
        partido.setId(Long.valueOf(movimientoMap.get(PROPERTY_PARTIDO_ID)));
        movimiento.setPartido(partido);

        TorneoJugador entra = new TorneoJugador();
        entra.setId(Long.valueOf(movimientoMap.get(PROPERTY_ENTRA_ID)));
        movimiento.setEntra(entra);

        TorneoJugador sale = new TorneoJugador();
        String idSale = movimientoMap.get(PROPERTY_SALE_ID);
        if (idSale != null && !idSale.isEmpty()) {
            sale.setId(Long.valueOf(idSale));
        } else {
            sale = null;
        }
        movimiento.setSale(sale);

        movimiento.setMinuto(Integer.valueOf(movimientoMap.get(PROPERTY_MINUTO)));
        movimiento.setSegundo(Integer.valueOf(movimientoMap.get(PROPERTY_SEGUNDO)));
        movimiento.setTipo(TipoMovimiento.valueOf(movimientoMap.get(PROPERTY_TIPO)));
        movimiento.setOrigen(OrigenEstadistica.valueOf(movimientoMap.get(PROPERTY_ORIGEN)));

        return movimiento;
    }

    private Map<String, Object> convertMovimientoToMap(Movimiento movimiento) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, movimiento.getId());
        map.put(PROPERTY_PARTIDO_ID, movimiento.getPartido().getId());
        map.put(PROPERTY_TIEMPO_DES, movimiento.getTiempoDescripcion());
        map.put(PROPERTY_MINUTO, movimiento.getMinuto());
        map.put(PROPERTY_SEGUNDO, movimiento.getSegundo());
        map.put(PROPERTY_TIPO, movimiento.getTipo());
        map.put(PROPERTY_ORIGEN, movimiento.getOrigen());
        map.put(PROPERTY_ENTRA_ID, movimiento.getEntra().getId());
        map.put(PROPERTY_ENTRA_NOMBRE, movimiento.getEntra().getJugador().getNombreCompleto());
        if (movimiento.getSale() != null) {
            map.put(PROPERTY_SALE_ID, movimiento.getSale().getId());
            map.put(PROPERTY_SALE_NOMBRE, movimiento.getSale().getJugador().getNombreCompleto());
        }
        return map;
    }
}
