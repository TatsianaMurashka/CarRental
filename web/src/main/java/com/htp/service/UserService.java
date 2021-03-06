package com.htp.service;

import com.htp.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long userId);

    User findOne(Long userId);

    List<User> search(String searchParam);

    User save(User user);

    User update(User user);

    int delete(Long userId);
}
