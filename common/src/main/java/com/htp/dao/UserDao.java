package com.htp.dao;

import com.htp.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByLogin(String username);

    User findOne(Long userId);

    List<User> search(String searchParam);

    User save(User user);

    User update(User user);

    int delete(Long userId);
}
