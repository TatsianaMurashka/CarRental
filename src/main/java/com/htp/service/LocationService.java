package com.htp.service;

import com.htp.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAll();

    Optional<Location> findById(Long locationId);

    Location findOne(Long locationId);

    Location save(Location location);

    Location update(Location location);

    int delete(Long locationId);
}
