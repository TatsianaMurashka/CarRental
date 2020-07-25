package com.htp.service;

import com.htp.domain.hibernate.HibernateCar;

import java.util.List;
import java.util.Optional;

public interface HibCarService {
    List<HibernateCar> findAll();

    Optional<HibernateCar> findById(Long carId);

    HibernateCar findOne(Long carId);

    HibernateCar save(HibernateCar car);
}
