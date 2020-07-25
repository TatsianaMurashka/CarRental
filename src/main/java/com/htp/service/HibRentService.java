package com.htp.service;

import com.htp.domain.hibernate.HibernateCar;
import com.htp.domain.hibernate.HibernateRent;

import java.util.List;
import java.util.Optional;

public interface HibRentService {
    List<HibernateRent> findAll();

    Optional<HibernateRent> findById(Long rentId);

    HibernateRent findOne(Long rentId);

    HibernateRent save(HibernateRent rent);
}
