package com.codigoartesanal.entuliga.services;

import com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO;

import java.util.List;

/**
 * Created by betuzo on 22/10/15.
 */
public interface EstadisticaService {
    List<EstadisticaJugadorDTO> listLideresPuntosByTorneo(Long idTorneo);
    List<EstadisticaJugadorDTO> listLideresRebotesByTorneo(Long idTorneo);
    List<EstadisticaJugadorDTO> listLideresAsistenciasByTorneo(Long idTorneo);
    List<EstadisticaJugadorDTO> listLideresBloqueosByTorneo(Long idTorneo);
    List<EstadisticaJugadorDTO> listLideresRobosByTorneo(Long idTorneo);
}
