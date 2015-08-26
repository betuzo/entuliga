package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.CanchaRepository;
import com.codigoartesanal.entuliga.repositories.GeoLocationRepository;
import com.codigoartesanal.entuliga.services.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by betuzo on 25/08/15.
 */
@Service
public class CanchaServiceImpl implements CanchaService {

    @Autowired
    CanchaRepository canchaRepository;

    @Autowired
    GeoLocationRepository geoLocationRepository;

    @Transactional
    @Override
    public Cancha createCancha(Map<String, String> canchaMap, User admin) {
        Cancha cancha = convertMapToCancha(canchaMap);
        cancha.setAdmin(admin);
        geoLocationRepository.save(cancha.getGeoLocation());
        cancha = canchaRepository.save(cancha);
        cancha.getAdmin().setUserRole(null);
        return cancha;
    }

    @Override
    public List<Map<String, Object>> listCanchaByUser(User user) {
        Iterator<Cancha> itCancha = canchaRepository.findAllByAdmin(user).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itCancha.hasNext()) {
            Cancha cancha = itCancha.next();
            Map<String, Object> dto = convertCanchaToMap(cancha);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public List<Map<String, Object>> listCanchaByTorneoAndContainName(Long idTorneo, String likeName) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        Iterator<Cancha> itCancha =
                canchaRepository.findAllNotInTorneoAndNombreContaining(torneo, likeName).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itCancha.hasNext()) {
            Cancha cancha = itCancha.next();
            Map<String, Object> dto = convertCanchaToMap(cancha);
            copy.add(dto);
        }
        return copy;    }

    private Map<String, Object> convertCanchaToMap(Cancha cancha) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, cancha.getId());
        map.put(PROPERTY_NOMBRE, cancha.getNombre());
        map.put(PROPERTY_ALIAS, cancha.getAlias());
        map.put(PROPERTY_DESCRIPCION, cancha.getDescripcion());
        if (cancha.getGeoLocation() == null)
            return map;
        map.put(PROPERTY_GEO_LOCATION_ID, cancha.getGeoLocation().getId());
        map.put(PROPERTY_CALLE, cancha.getGeoLocation().getCalle());
        map.put(PROPERTY_NO_EXTERIOR, cancha.getGeoLocation().getNoExterior());
        map.put(PROPERTY_NO_INTERIOR, cancha.getGeoLocation().getNoInterior());
        map.put(PROPERTY_CODIGO_POSTAL, cancha.getGeoLocation().getCodigoPostal());
        map.put(PROPERTY_LATITUDE, cancha.getGeoLocation().getLatitude());
        map.put(PROPERTY_LONGITUDE, cancha.getGeoLocation().getLongitude());
        if (cancha.getGeoLocation().getColonia() == null)
            return map;
        map.put(PROPERTY_COLONIA_ID, cancha.getGeoLocation().getColonia().getId());
        map.put(PROPERTY_COLONIA_DESC, cancha.getGeoLocation().getColonia().getNombre());
        if (cancha.getGeoLocation().getColonia().getMunicipio() == null)
            return map;
        map.put(PROPERTY_MUNICIPIO_ID, cancha.getGeoLocation().getColonia().getMunicipio().getId());
        map.put(PROPERTY_MUNICIPIO_DESC, cancha.getGeoLocation().getColonia().getMunicipio().getNombre());
        if (cancha.getGeoLocation().getColonia().getMunicipio().getEstado() == null)
            return map;
        map.put(PROPERTY_ESTADO_ID, cancha.getGeoLocation().getColonia().getMunicipio().getEstado().getId());
        map.put(PROPERTY_ESTADO_DESC, cancha.getGeoLocation().getColonia().getMunicipio().getEstado().getNombre());
        if (cancha.getGeoLocation().getColonia().getMunicipio().getEstado().getPais() == null)
            return map;
        map.put(PROPERTY_PAIS_ID, cancha.getGeoLocation().getColonia().getMunicipio().getEstado().getPais().getId());
        map.put(PROPERTY_PAIS_DESC, cancha.getGeoLocation().getColonia().getMunicipio().getEstado().getPais().getNombre());

        return map;
    }

    private Cancha convertMapToCancha(Map<String, String> canchaMap) {
        Cancha cancha = new Cancha();
        GeoLocation geoLocation = new GeoLocation();
        if (canchaMap.containsKey(PROPERTY_ID)) {
            cancha = this.get(Long.valueOf(canchaMap.get(PROPERTY_ID)));
            geoLocation = cancha.getGeoLocation();
        }
        cancha.setNombre(canchaMap.get(PROPERTY_NOMBRE));
        cancha.setAlias(canchaMap.get(PROPERTY_ALIAS));
        cancha.setDescripcion(canchaMap.get(PROPERTY_DESCRIPCION));

        geoLocation.setCalle(canchaMap.get(PROPERTY_CALLE));
        geoLocation.setNoExterior(canchaMap.get(PROPERTY_NO_EXTERIOR));
        String noInterior = canchaMap.get(PROPERTY_NO_INTERIOR);
        if (noInterior != null && !noInterior.isEmpty()) {
            geoLocation.setNoInterior(noInterior);
        }
        geoLocation.setCodigoPostal(canchaMap.get(PROPERTY_CODIGO_POSTAL));
        String latitude = canchaMap.get(PROPERTY_LATITUDE);
        if (latitude != null && !latitude.isEmpty()) {
            geoLocation.setLatitude(Double.parseDouble(latitude));
        }
        String longitude = canchaMap.get(PROPERTY_LONGITUDE);
        if (longitude != null && !longitude.isEmpty()) {
            geoLocation.setLongitude(Double.parseDouble(longitude));
        }
        Colonia colonia = new Colonia();
        colonia.setId(Long.valueOf(canchaMap.get(PROPERTY_COLONIA_ID)));
        geoLocation.setColonia(colonia);
        cancha.setGeoLocation(geoLocation);
        return cancha;
    }

    private Cancha get(Long idLiga){
        return this.canchaRepository.findOne(idLiga);
    }
}
