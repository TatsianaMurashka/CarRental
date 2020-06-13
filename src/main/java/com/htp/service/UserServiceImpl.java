package com.htp.service;

import com.htp.dao.UserDao;
import com.htp.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(@Qualifier("userRepositoryJdbcTemplate") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(Long userId) {
        return userDao.delete(userId);
    }
}
