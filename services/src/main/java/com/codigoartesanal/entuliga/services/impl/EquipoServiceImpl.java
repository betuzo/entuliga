package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.Equipo;
import com.codigoartesanal.entuliga.model.Torneo;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.EquipoRepository;
import com.codigoartesanal.entuliga.services.EquipoService;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 21/05/15.
 */
@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    PhotoService photoService;

    @Override
    public Map<String, Object> createEquipo(Map<String, String> equipoMap, User admin) {
        Equipo equipo = convertMapToEquipo(equipoMap);
        equipo.setAdmin(admin);
        return convertEquipoToMap(equipoRepository.save(equipo));
    }

    @Override
    public DeleteStatusEnum deleteEquipo(Long idEquipo) {
        try {
            equipoRepository.delete(idEquipo);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
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

    @Override
    public void updateLogoByEquipo(String logo, Long idEquipo) {
        equipoRepository.updateLogoByIdEquipo(logo, idEquipo);
    }

    private Map<String, Object> convertEquipoToMap(Equipo equipo) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, equipo.getId());
        map.put(PROPERTY_NOMBRE, equipo.getNombre());
        map.put(PROPERTY_ALIAS_EQUIPO, equipo.getAliasEquipo());
        map.put(PROPERTY_LOGO_EQUIPO, equipo.getRutaLogoEquipo());
        String pathWebFull = photoService.getValidPathWebLogo(equipo.getRutaLogoEquipo(), null);
        map.put(PROPERTY_RUTA_LOGO_EQUIPO, pathWebFull);
        map.put(PROPERTY_HAS_LOGO_EQUIPO, !pathWebFull.contains(PathPhoto.EQUIPO_DEFAULT.getPath()));
        return map;
    }

    private Equipo convertMapToEquipo(Map<String, String> equipoMap) {
        Equipo equipo = new Equipo();
        if (equipoMap.containsKey(PROPERTY_ID)) {
            equipo = this.get(Long.valueOf(equipoMap.get(PROPERTY_ID)));
        }
        equipo.setNombre(equipoMap.get(PROPERTY_NOMBRE));
        equipo.setAliasEquipo(equipoMap.get(PROPERTY_ALIAS_EQUIPO));
        equipo.setRutaLogoEquipo(equipoMap.get(PROPERTY_LOGO_EQUIPO));
        return equipo;
    }

    private Equipo get(Long idEquipo) {
        return this.equipoRepository.findOne(idEquipo);
    }
}
