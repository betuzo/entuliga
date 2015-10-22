package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.config.PersistenceTestConfig;
import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by betuzo on 21/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class})
public class TorneoRepositoryTest {

    @Autowired
    TorneoRepository torneoRepository;

    @Test
    public void testFindAllByLiga() {
        Liga liga = new Liga();
        liga.setId(1L);
        List<Torneo> torneos = torneoRepository.findAllByLiga(liga);

        Assert.assertNotNull(torneos);
    }

    @Test
    public void testFindAllLideresPuntosByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findAllLideresPuntosByTorneo(torneo);

        Assert.assertNotNull(estadisticas.size()>0);
    }

    @Test
    public void testFindAllLideresRebotesByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findAllLideresRebotesByTorneo(torneo);

        Assert.assertNotNull(estadisticas.size()==0);
    }

    @Test
    public void testFindAllLideresAsistenciasByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findAllLideresAsistenciasByTorneo(torneo);

        Assert.assertNotNull(estadisticas.size()==0);
    }

    @Test
    public void testFindAllLideresBloqueosByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findAllLideresBloqueosByTorneo(torneo);

        Assert.assertNotNull(estadisticas.size()==0);
    }
    @Test
    public void testFindAllLideresRobosByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findAllLideresRobosByTorneo(torneo);

        Assert.assertNotNull(estadisticas.size()==0);
    }
}
