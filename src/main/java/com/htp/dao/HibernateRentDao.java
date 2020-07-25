package com.htp.dao;

import com.htp.domain.hibernate.HibernateRent;

import java.util.List;
import java.util.Optional;

public interface HibernateRentDao {
    List<HibernateRent> findAll();

    Optional<HibernateRent> findById(Long rentId);

    HibernateRent findOne(Long rentId);

    List<HibernateRent> search(String searchParam);

    HibernateRent save(HibernateRent rent);

    HibernateRent update(HibernateRent rent);

    int delete(Long rentId);
}
