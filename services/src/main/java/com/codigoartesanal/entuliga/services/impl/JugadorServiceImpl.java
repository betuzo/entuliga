package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.GeoLocationRepository;
import com.codigoartesanal.entuliga.repositories.JugadorRepository;
import com.codigoartesanal.entuliga.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 22/05/15.
 */
@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    JugadorRepository jugadorRepository;

    @Autowired
    GeoLocationRepository geoLocationRepository;

    @Override
    public Map<String, Object> createJugador(Map<String, String> jugadorMap, User admin) {
        Jugador jugador = convertMapToJugador(jugadorMap);
        jugador.setAdmin(admin);
        geoLocationRepository.save(jugador.getGeoLocation());
        return convertJugadorToMap(jugadorRepository.save(jugador));
    }

    @Override
    public List<Map<String, Object>> listJugadorByAdmin(User user) {
        Iterator<Jugador> itJugador = jugadorRepository.findAllByAdmin(user).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itJugador.hasNext()) {
            Jugador jugador = itJugador.next();
            Map<String, Object> dto = convertJugadorToMap(jugador);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertJugadorToMap(Jugador jugador) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, jugador.getId());
        map.put(PROPERTY_NOMBRE, jugador.getNombre());
        map.put(PROPERTY_PATERNO, jugador.getPaterno());
        map.put(PROPERTY_MATERNO, jugador.getMaterno());
        map.put(PROPERTY_RUTA_FOTO, jugador.getRutaFoto());
        map.put(PROPERTY_SEXO, jugador.getSexo());
        map.put(PROPERTY_FECHA_REGISTRO, jugador.getFechaRegistro());
        if (jugador.getGeoLocation() == null)
            return map;
        map.put(PROPERTY_GEO_LOCATION_ID, jugador.getGeoLocation().getId());
        map.put(PROPERTY_CALLE, jugador.getGeoLocation().getCalle());
        map.put(PROPERTY_NO_EXTERIOR, jugador.getGeoLocation().getNoExterior());
        map.put(PROPERTY_NO_INTERIOR, jugador.getGeoLocation().getNoInterior());
        map.put(PROPERTY_CODIGO_POSTAL, jugador.getGeoLocation().getCodigoPostal());
        map.put(PROPERTY_LATITUDE, jugador.getGeoLocation().getLatitude());
        map.put(PROPERTY_LONGITUDE, jugador.getGeoLocation().getLongitude());
        if (jugador.getGeoLocation().getColonia() == null)
            return map;
        map.put(PROPERTY_COLONIA_ID, jugador.getGeoLocation().getColonia().getId());
        map.put(PROPERTY_COLONIA_DESC, jugador.getGeoLocation().getColonia().getNombre());
        if (jugador.getGeoLocation().getColonia().getMunicipio() == null)
            return map;
        map.put(PROPERTY_MUNICIPIO_ID, jugador.getGeoLocation().getColonia().getMunicipio().getId());
        map.put(PROPERTY_MUNICIPIO_DESC, jugador.getGeoLocation().getColonia().getMunicipio().getNombre());
        if (jugador.getGeoLocation().getColonia().getMunicipio().getEstado() == null)
            return map;
        map.put(PROPERTY_ESTADO_ID, jugador.getGeoLocation().getColonia().getMunicipio().getEstado().getId());
        map.put(PROPERTY_ESTADO_DESC, jugador.getGeoLocation().getColonia().getMunicipio().getEstado().getNombre());
        if (jugador.getGeoLocation().getColonia().getMunicipio().getEstado().getPais() == null)
            return map;
        map.put(PROPERTY_PAIS_ID, jugador.getGeoLocation().getColonia().getMunicipio().getEstado().getPais().getId());
        map.put(PROPERTY_PAIS_DESC, jugador.getGeoLocation().getColonia().getMunicipio().getEstado().getPais().getNombre());

        return map;
    }

    private Jugador convertMapToJugador(Map<String, String> ligaMap) {
        Jugador jugador = new Jugador();
        GeoLocation geoLocation = new GeoLocation();
        if (ligaMap.containsKey(PROPERTY_ID)) {
            jugador = this.get(Long.valueOf(ligaMap.get(PROPERTY_ID)));
            geoLocation = jugador.getGeoLocation();
        }
        jugador.setNombre(ligaMap.get(PROPERTY_NOMBRE));
        jugador.setPaterno(ligaMap.get(PROPERTY_PATERNO));
        jugador.setMaterno(ligaMap.get(PROPERTY_MATERNO));
        jugador.setRutaFoto(ligaMap.get(PROPERTY_RUTA_FOTO));
        jugador.setSexo(Sexo.valueOf(ligaMap.get(PROPERTY_SEXO)));
        jugador.setFechaRegistro(new Date(Long.valueOf(ligaMap.get(PROPERTY_FECHA_REGISTRO))));

        geoLocation.setCalle(ligaMap.get(PROPERTY_CALLE));
        geoLocation.setNoExterior(ligaMap.get(PROPERTY_NO_EXTERIOR));
        String noInterior = ligaMap.get(PROPERTY_NO_INTERIOR);
        if (noInterior != null && !noInterior.isEmpty()) {
            geoLocation.setNoInterior(noInterior);
        }
        geoLocation.setCodigoPostal(ligaMap.get(PROPERTY_CODIGO_POSTAL));
        String latitude = ligaMap.get(PROPERTY_LATITUDE);
        if (latitude != null && !latitude.isEmpty()) {
            geoLocation.setLatitude(Double.parseDouble(latitude));
        }
        String longitude = ligaMap.get(PROPERTY_LONGITUDE);
        if (longitude != null && !longitude.isEmpty()) {
            geoLocation.setLongitude(Double.parseDouble(longitude));
        }
        Colonia colonia = new Colonia();
        colonia.setId(Long.valueOf(ligaMap.get(PROPERTY_COLONIA_ID)));
        geoLocation.setColonia(colonia);
        jugador.setGeoLocation(geoLocation);
        return jugador;
    }

    private Jugador get(Long idJugador){
        return this.jugadorRepository.findOne(idJugador);
    }
}
