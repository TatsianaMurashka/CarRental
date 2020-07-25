package com.htp.dao;

import com.htp.domain.hibernate.HibernateCar;

import java.util.List;
import java.util.Optional;

public interface HibernateCarDao {
    List<HibernateCar> findAll();

    Optional<HibernateCar> findById(Long carId);

    HibernateCar findOne(Long carId);

    List<HibernateCar> search(String searchParam);

    HibernateCar save(HibernateCar car);

    HibernateCar update(HibernateCar car);

    int delete(Long carId);
}
