package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO;

import java.util.List;

/**
 * Created by betuzo on 22/10/15.
 */
public interface EstadisticaService {
    List<EstadisticaJugadorDTO> listLideresTodosByTorneoTopFive(Long idTorneo);
}
