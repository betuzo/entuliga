package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.*;
import com.codigoartesanal.entuliga.repositories.ArbitroRepository;
import com.codigoartesanal.entuliga.repositories.GeoLocationRepository;
import com.codigoartesanal.entuliga.services.ArbitroService;
import com.codigoartesanal.entuliga.services.OriginPhoto;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PathWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by betuzo on 22/05/15.
 */
@Service
public class ArbitroServiceImpl implements ArbitroService {

    @Autowired
    ArbitroRepository arbitroRepository;

    @Autowired
    GeoLocationRepository geoLocationRepository;

    @Autowired
    PathWebService pathWebService;

    @Override
    public Map<String, Object> createArbitro(Map<String, String> arbitroMap, User admin) {
        Arbitro arbitro = convertMapToArbitro(arbitroMap);
        arbitro.setAdmin(admin);
        geoLocationRepository.save(arbitro.getGeoLocation());
        return convertArbitroToMap(arbitroRepository.save(arbitro));
    }

    @Override
    public DeleteStatusEnum deleteArbitro(Long idArbitro) {
        try {
            arbitroRepository.delete(idArbitro);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    @Override
    public List<Map<String,Object>> listArbitroByAdmin(User user) {
        Iterator<Arbitro> itArbitro = arbitroRepository.findAllByAdmin(user).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itArbitro.hasNext()) {
            Arbitro arbitro = itArbitro.next();
            Map<String, Object> dto = convertArbitroToMap(arbitro);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertArbitroToMap(Arbitro arbitro) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, arbitro.getId());
        map.put(PROPERTY_NOMBRE, arbitro.getNombre());
        map.put(PROPERTY_PATERNO, arbitro.getPaterno());
        map.put(PROPERTY_MATERNO, arbitro.getMaterno());
        map.put(PROPERTY_FOTO_ARBITRO, arbitro.getRutaFoto());
        String pathWebFull = pathWebService.getValidPathWebFoto(arbitro.getRutaFoto(), OriginPhoto.ARBITRO);
        map.put(PROPERTY_RUTA_FOTO_ARBITRO, pathWebFull);
        map.put(PROPERTY_HAS_FOTO_ARBITRO, !pathWebFull.contains(PathPhoto.ARBITRO_DEFAULT.getPath()));
        map.put(PROPERTY_SEXO, arbitro.getSexo());
        map.put(PROPERTY_FECHA_REGISTRO, arbitro.getFechaRegistro());
        if (arbitro.getGeoLocation() == null)
            return map;
        map.put(PROPERTY_GEO_LOCATION_ID, arbitro.getGeoLocation().getId());
        map.put(PROPERTY_CALLE, arbitro.getGeoLocation().getCalle());
        map.put(PROPERTY_NO_EXTERIOR, arbitro.getGeoLocation().getNoExterior());
        map.put(PROPERTY_NO_INTERIOR, arbitro.getGeoLocation().getNoInterior());
        map.put(PROPERTY_CODIGO_POSTAL, arbitro.getGeoLocation().getCodigoPostal());
        map.put(PROPERTY_LATITUDE, arbitro.getGeoLocation().getLatitude());
        map.put(PROPERTY_LONGITUDE, arbitro.getGeoLocation().getLongitude());
        if (arbitro.getGeoLocation().getColonia() == null)
            return map;
        map.put(PROPERTY_COLONIA_ID, arbitro.getGeoLocation().getColonia().getId());
        map.put(PROPERTY_COLONIA_DESC, arbitro.getGeoLocation().getColonia().getNombre());
        if (arbitro.getGeoLocation().getColonia().getMunicipio() == null)
            return map;
        map.put(PROPERTY_MUNICIPIO_ID, arbitro.getGeoLocation().getColonia().getMunicipio().getId());
        map.put(PROPERTY_MUNICIPIO_DESC, arbitro.getGeoLocation().getColonia().getMunicipio().getNombre());
        if (arbitro.getGeoLocation().getColonia().getMunicipio().getEstado() == null)
            return map;
        map.put(PROPERTY_ESTADO_ID, arbitro.getGeoLocation().getColonia().getMunicipio().getEstado().getId());
        map.put(PROPERTY_ESTADO_DESC, arbitro.getGeoLocation().getColonia().getMunicipio().getEstado().getNombre());
        if (arbitro.getGeoLocation().getColonia().getMunicipio().getEstado().getPais() == null)
            return map;
        map.put(PROPERTY_PAIS_ID, arbitro.getGeoLocation().getColonia().getMunicipio().getEstado().getPais().getId());
        map.put(PROPERTY_PAIS_DESC, arbitro.getGeoLocation().getColonia().getMunicipio().getEstado().getPais().getNombre());

        return map;
    }

    private Arbitro convertMapToArbitro(Map<String, String> arbitroMap) {
        Arbitro arbitro = new Arbitro();
        GeoLocation geoLocation = new GeoLocation();
        if (arbitroMap.containsKey(PROPERTY_ID)) {
            arbitro = this.get(Long.valueOf(arbitroMap.get(PROPERTY_ID)));
            geoLocation = arbitro.getGeoLocation();
        }
        arbitro.setNombre(arbitroMap.get(PROPERTY_NOMBRE));
        arbitro.setPaterno(arbitroMap.get(PROPERTY_PATERNO));
        arbitro.setMaterno(arbitroMap.get(PROPERTY_MATERNO));
        arbitro.setRutaFoto(arbitroMap.get(PROPERTY_FOTO_ARBITRO));
        arbitro.setSexo(Sexo.valueOf(arbitroMap.get(PROPERTY_SEXO)));
        arbitro.setFechaRegistro(new Date(Long.valueOf(arbitroMap.get(PROPERTY_FECHA_REGISTRO))));

        geoLocation.setCalle(arbitroMap.get(PROPERTY_CALLE));
        geoLocation.setNoExterior(arbitroMap.get(PROPERTY_NO_EXTERIOR));
        String noInterior = arbitroMap.get(PROPERTY_NO_INTERIOR);
        if (noInterior != null && !noInterior.isEmpty()) {
            geoLocation.setNoInterior(noInterior);
        }
        geoLocation.setCodigoPostal(arbitroMap.get(PROPERTY_CODIGO_POSTAL));
        String latitude = arbitroMap.get(PROPERTY_LATITUDE);
        if (latitude != null && !latitude.isEmpty()) {
            geoLocation.setLatitude(BigDecimal.valueOf(Double.parseDouble(latitude)));
        }
        String longitude = arbitroMap.get(PROPERTY_LONGITUDE);
        if (longitude != null && !longitude.isEmpty()) {
            geoLocation.setLongitude(BigDecimal.valueOf(Double.parseDouble(longitude)));
        }
        Colonia colonia = new Colonia();
        colonia.setId(Long.valueOf(arbitroMap.get(PROPERTY_COLONIA_ID)));
        geoLocation.setColonia(colonia);
        arbitro.setGeoLocation(geoLocation);
        return arbitro;
    }

    private Arbitro get(Long idArbitro){
        return this.arbitroRepository.findOne(idArbitro);
    }
}
