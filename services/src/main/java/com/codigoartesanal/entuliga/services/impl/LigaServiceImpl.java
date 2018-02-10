package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Colonia;
import com.codigoartesanal.entuliga.model.GeoLocation;
import com.codigoartesanal.entuliga.model.Liga;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.GeoLocationRepository;
import com.codigoartesanal.entuliga.repositories.LigaRepository;
import com.codigoartesanal.entuliga.services.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

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
    public Map<String,Object> createLiga(Map<String, String> ligaMap, User admin) {
        Liga liga = convertMapToLiga(ligaMap);
        liga.setAdmin(admin);
        geoLocationRepository.save(liga.getGeoLocation());
        return convertLigaToMap(ligaRepository.save(liga));
    }

    @Override
    public List<Map<String, Object>> listLigaByUser(User user) {
        Iterator<Liga> itLiga = ligaRepository.findAllByAdmin(user).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itLiga.hasNext()) {
            Liga liga = itLiga.next();
            Map<String, Object> dto = convertLigaToMap(liga);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public DeleteStatusEnum deleteLiga(Long idLiga) {
        try {
            ligaRepository.deleteById(idLiga);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    private Map<String, Object> convertLigaToMap(Liga liga) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, liga.getId());
        map.put(PROPERTY_NOMBRE, liga.getNombre());
        map.put(PROPERTY_NOMBRE_COMPLETO, liga.getNombreCompleto());
        map.put(PROPERTY_TELEFONO, liga.getTelefono());
        if (liga.getGeoLocation() == null)
            return map;
        map.put(PROPERTY_GEO_LOCATION_ID, liga.getGeoLocation().getId());
        map.put(PROPERTY_CALLE, liga.getGeoLocation().getCalle());
        map.put(PROPERTY_NO_EXTERIOR, liga.getGeoLocation().getNoExterior());
        map.put(PROPERTY_NO_INTERIOR, liga.getGeoLocation().getNoInterior());
        map.put(PROPERTY_CODIGO_POSTAL, liga.getGeoLocation().getColonia().getCodigoPostal());
        map.put(PROPERTY_LATITUDE, liga.getGeoLocation().getLatitude());
        map.put(PROPERTY_LONGITUDE, liga.getGeoLocation().getLongitude());
        if (liga.getGeoLocation().getColonia() == null)
            return map;
        map.put(PROPERTY_COLONIA_ID, liga.getGeoLocation().getColonia().getId());
        map.put(PROPERTY_COLONIA_DESC, liga.getGeoLocation().getColonia().getNombre());
        if (liga.getGeoLocation().getColonia().getMunicipio() == null)
            return map;
        map.put(PROPERTY_MUNICIPIO_ID, liga.getGeoLocation().getColonia().getMunicipio().getId());
        map.put(PROPERTY_MUNICIPIO_DESC, liga.getGeoLocation().getColonia().getMunicipio().getNombre());
        if (liga.getGeoLocation().getColonia().getMunicipio().getEstado() == null)
            return map;
        map.put(PROPERTY_ESTADO_ID, liga.getGeoLocation().getColonia().getMunicipio().getEstado().getId());
        map.put(PROPERTY_ESTADO_DESC, liga.getGeoLocation().getColonia().getMunicipio().getEstado().getNombre());
        if (liga.getGeoLocation().getColonia().getMunicipio().getEstado().getPais() == null)
            return map;
        map.put(PROPERTY_PAIS_ID, liga.getGeoLocation().getColonia().getMunicipio().getEstado().getPais().getId());
        map.put(PROPERTY_PAIS_DESC, liga.getGeoLocation().getColonia().getMunicipio().getEstado().getPais().getNombre());

        return map;
    }

    private Liga convertMapToLiga(Map<String, String> ligaMap) {
        Liga liga = new Liga();
        GeoLocation geoLocation = new GeoLocation();
        if (ligaMap.containsKey(PROPERTY_ID)) {
            liga = this.get(Long.valueOf(ligaMap.get(PROPERTY_ID)));
            geoLocation = liga.getGeoLocation();
        }
        liga.setNombre(ligaMap.get(PROPERTY_NOMBRE));
        liga.setNombreCompleto(ligaMap.get(PROPERTY_NOMBRE_COMPLETO));
        liga.setTelefono(ligaMap.get(PROPERTY_TELEFONO));

        geoLocation.setCalle(ligaMap.get(PROPERTY_CALLE));
        geoLocation.setNoExterior(ligaMap.get(PROPERTY_NO_EXTERIOR));
        String noInterior = ligaMap.get(PROPERTY_NO_INTERIOR);
        if (noInterior != null && !noInterior.isEmpty()) {
            geoLocation.setNoInterior(noInterior);
        }
        String latitude = ligaMap.get(PROPERTY_LATITUDE);
        if (latitude != null && !latitude.isEmpty()) {
            geoLocation.setLatitude(BigDecimal.valueOf(Double.parseDouble(latitude)));
        }
        String longitude = ligaMap.get(PROPERTY_LONGITUDE);
        if (longitude != null && !longitude.isEmpty()) {
            geoLocation.setLongitude(BigDecimal.valueOf(Double.parseDouble(longitude)));
        }
        Colonia colonia = new Colonia();
        colonia.setId(Long.valueOf(ligaMap.get(PROPERTY_COLONIA_ID)));
        geoLocation.setColonia(colonia);
        liga.setGeoLocation(geoLocation);
        return liga;
    }

    private Liga get(Long idLiga){
        return this.ligaRepository.findById(idLiga).get();
    }
}
