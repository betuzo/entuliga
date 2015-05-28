package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.EquipoRepository;
import com.codigoartesanal.entuliga.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 21/05/15.
 */
@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    EquipoRepository equipoRepository;

    @Override
    public Map<String, Object> createEquipo(Map<String, String> equipoMap, User admin) {
        Equipo equipo = convertMapToEquipo(equipoMap);
        equipo.setAdmin(admin);
        return convertEquipoToMap(equipoRepository.save(equipo));
    }

    @Override
    public List<Map<String, Object>> listEquipoByAdmin(User user) {
        Iterator<Equipo> itEquipo = equipoRepository.findAllByAdmin(user).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itEquipo.hasNext()) {
            Equipo equipo = itEquipo.next();
            Map<String, Object> dto = convertEquipoToMap(equipo);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public List<Map<String, Object>> listEquipoByTorneoAndContainName(Long idTorneo, String likeName) {
        Torneo torneo = new Torneo();
        torneo.setId(idTorneo);
        Iterator<Equipo> itEquipo =
                equipoRepository.findAllNotInTorneoAndNombreContaining(torneo, likeName).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itEquipo.hasNext()) {
            Equipo equipo = itEquipo.next();
            Map<String, Object> dto = convertEquipoToMap(equipo);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertEquipoToMap(Equipo equipo) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, equipo.getId());
        map.put(PROPERTY_NOMBRE, equipo.getNombre());
        map.put(PROPERTY_ALIAS_EQUIPO, equipo.getAliasEquipo());
        map.put(PROPERTY_RUTA_LOGO_EQUIPO, equipo.getRutaLogoEquipo());
        return map;
    }

    private Equipo convertMapToEquipo(Map<String, String> equipoMap) {
        Equipo equipo = new Equipo();
        if (equipoMap.containsKey(PROPERTY_ID)) {
            equipo = this.get(Long.valueOf(equipoMap.get(PROPERTY_ID)));
        }
        equipo.setNombre(equipoMap.get(PROPERTY_NOMBRE));
        equipo.setAliasEquipo(equipoMap.get(PROPERTY_ALIAS_EQUIPO));
        equipo.setRutaLogoEquipo(equipoMap.get(PROPERTY_RUTA_LOGO_EQUIPO));
        return equipo;
    }

    private Equipo get(Long idEquipo) {
        return this.equipoRepository.findOne(idEquipo);
    }
}
