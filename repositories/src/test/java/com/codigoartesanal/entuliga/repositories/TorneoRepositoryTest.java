package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.config.PersistenceConfig;
import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.dto.EstadisticaJugadorDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by betuzo on 21/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@PropertySource("classpath:application.properties")
@ActiveProfiles(value = "test")
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
        PageRequest pageRequest = new PageRequest(0, 5);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findLimitLideresPuntosByTorneo(torneo, pageRequest);

        Assert.assertNotNull(estadisticas.size()<5);
    }

    @Test
    public void testFindAllLideresRebotesByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        PageRequest pageRequest = new PageRequest(0, 5);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findLimitLideresRebotesByTorneo(torneo, pageRequest);

        Assert.assertNotNull(estadisticas.size()<5);
    }

    @Test
    public void testFindAllLideresAsistenciasByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        PageRequest pageRequest = new PageRequest(0, 5);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findLimitLideresAsistenciasByTorneo(torneo, pageRequest);

        Assert.assertNotNull(estadisticas.size()<5);
    }

    @Test
    public void testFindAllLideresBloqueosByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        PageRequest pageRequest = new PageRequest(0, 5);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findLimitLideresBloqueosByTorneo(torneo, pageRequest);

        Assert.assertNotNull(estadisticas.size()<5);
    }
    @Test
    public void testFindAllLideresRobosByTorneo() {
        Torneo torneo = new Torneo();
        torneo.setId(1L);
        PageRequest pageRequest = new PageRequest(0, 5);
        List<EstadisticaJugadorDTO> estadisticas = torneoRepository.findLimitLideresRobosByTorneo(torneo, pageRequest);

        Assert.assertNotNull(estadisticas.size()<5);
    }
}
