package com.htp.service;

import com.htp.dao.LocationDao;
import com.htp.domain.Location;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationDao locationDao;

    public LocationServiceImpl(@Qualifier("locationRepositoryJdbcTemplate") LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public List<Location> findAll() {
        return locationDao.findAll();
    }

    @Override
    public Optional<Location> findById(Long locationId) {
        return locationDao.findById(locationId);
    }

    @Override
    public Location findOne(Long locationId) {
        return locationDao.findOne(locationId);
    }

    @Override
    public List<Location> search(String searchParam) {
        return locationDao.search(searchParam);
    }

    @Override
    public Location save(Location location) {
        return locationDao.save(location);
    }

    @Override
    public Location update(Location location) {
        return locationDao.update(location);
    }

    @Override
    public int delete(Long locationId) {
        return locationDao.delete(locationId);
    }
}
