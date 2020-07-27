package com.htp.dao;

import com.htp.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibernateUserDao {
    List<HibernateUser> findAll();

    Optional<HibernateUser> findById(Long userId);

    Optional<HibernateUser> findByLogin(String username);

    HibernateUser findOne(Long userId);

    List<Object> search(String searchParam);

    HibernateUser save(HibernateUser user);

    HibernateUser update(HibernateUser user);

    int delete(Long userId);
}
