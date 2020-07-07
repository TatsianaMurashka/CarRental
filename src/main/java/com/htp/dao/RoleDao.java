package com.htp.dao;

import com.htp.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {

    List<Role> findAll();

    List<Role> findByUserId(Long userId);

    Optional<Role> findByLogin(String username);

    Role findOne(Long id);

    List<Role> search(String searchParam);

    Role save(Role role);

    Role update(Role role);

    int delete(Long id);
}
