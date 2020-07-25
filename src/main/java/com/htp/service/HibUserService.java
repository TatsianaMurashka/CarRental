package com.htp.service;

import com.htp.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibUserService {
    List<HibernateUser> findAll();

    Optional<HibernateUser> findById(Long userId);

    HibernateUser findOne(Long userId);

    HibernateUser save(HibernateUser user);
}
