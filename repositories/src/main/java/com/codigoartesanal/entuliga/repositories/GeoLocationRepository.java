package com.codigoartesanal.entuliga.repositories;

import com.codigoartesanal.entuliga.model.GeoLocation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by betuzo on 12/05/15.
 */
public interface GeoLocationRepository extends CrudRepository<GeoLocation, Long> {
}
