package com.htp.service;

import com.htp.dao.HibernateUserDao;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateUserService implements HibUserService {

    private HibernateUserDao hibernateUserRepository;

    public HibernateUserService(HibernateUserDao hibernateUserRepository) {
        this.hibernateUserRepository = hibernateUserRepository;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateUser save(HibernateUser user){
        return hibernateUserRepository.save(user);
    }

    public List<HibernateUser> findAll() {
        return hibernateUserRepository.findAll();
    }

    public Optional<HibernateUser> findById(Long userId) {
        return hibernateUserRepository.findById(userId);
    }

    public HibernateUser findOne(Long userId) {
        return hibernateUserRepository.findOne(userId);
    }

    @Override
    public List<Object> search(String query) {
        return null;
    }
}
