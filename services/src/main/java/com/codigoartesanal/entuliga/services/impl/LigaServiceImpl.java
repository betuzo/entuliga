package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Colonia;
import com.codigoartesanal.entuliga.model.GeoLocation;
import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.GeoLocationRepository;
import com.codigoartesanal.entuliga.repositories.LigaRepository;
import com.codigoartesanal.entuliga.services.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by betuzo on 12/05/15.
 */
@Service
public class LigaServiceImpl implements LigaService {

    @Autowired
    LigaRepository ligaRepository;

    @Autowired
    GeoLocationRepository geoLocationRepository;

    @Transactional
    @Override
    public Liga createLiga(Map<String, String> ligaMap, User admin) {
        Liga liga = convertMapToLiga(ligaMap);
        liga.setAdmin(admin);
        geoLocationRepository.save(liga.getGeoLocation());
        return ligaRepository.save(liga);
    }

    private Liga convertMapToLiga(Map<String, String> ligaMap) {
        Liga liga = new Liga();
        liga.setNombre(ligaMap.get(PROPERTY_NOMBRE));
        liga.setNombreCompleto(ligaMap.get(PROPERTY_NOMBRE_COMPLETO));
        liga.setTelefono(ligaMap.get(PROPERTY_TELEFONO));

        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setCalle(ligaMap.get(PROPERTY_CALLE));
        geoLocation.setNoExterior(ligaMap.get(PROPERTY_NO_EXTERIOR));
        geoLocation.setNoInterior(ligaMap.get(PROPERTY_NO_INTERIOR));
        geoLocation.setCodigoPostal(ligaMap.get(PROPERTY_CODIGO_POSTAL));
        geoLocation.setLatitude(Double.parseDouble(ligaMap.get(PROPERTY_LATITUDE)));
        geoLocation.setLongitude(Double.parseDouble(ligaMap.get(PROPERTY_LONGITUDE)));

        Colonia colonia = new Colonia();
        colonia.setId(Long.valueOf(ligaMap.get(PROPERTY_COLONIA_ID)));
        geoLocation.setColonia(colonia);
        liga.setGeoLocation(geoLocation);
        return liga;
    }
}
