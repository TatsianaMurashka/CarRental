package com.htp.service;

import com.htp.dao.HibernateCarDao;
import com.htp.dao.HibernateUserDao;
import com.htp.domain.hibernate.HibernateCar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateCarService implements HibCarService{

    private HibernateCarDao hibernateCarRepository;

    public HibernateCarService(HibernateCarDao hibernateCarRepository) {
        this.hibernateCarRepository = hibernateCarRepository;
    }

    @Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateCar save(HibernateCar car) {
        return hibernateCarRepository.save(car);
    }

    public List<HibernateCar> findAll() {
        return hibernateCarRepository.findAll();
    }

    public Optional<HibernateCar> findById(Long carId) {
        return hibernateCarRepository.findById(carId);
    }

    public HibernateCar findOne(Long carId) {
        return hibernateCarRepository.findOne(carId);
    }


}
