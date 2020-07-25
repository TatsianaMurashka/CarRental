package com.htp.service;

import com.htp.dao.HibernateRentDao;
import com.htp.domain.hibernate.HibernateRent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HibernateRentService implements HibRentService{

    private HibernateRentDao hibernateRentRepository;

    public HibernateRentService(HibernateRentDao hibernateRentRepository) {
        this.hibernateRentRepository = hibernateRentRepository;
    }

    @Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public HibernateRent save(HibernateRent rent) {
        return hibernateRentRepository.save(rent);
    }

    public List<HibernateRent> findAll() {
        return hibernateRentRepository.findAll();
    }


    public Optional<HibernateRent> findById(Long rentId) {
        return hibernateRentRepository.findById(rentId);
    }


    public HibernateRent findOne(Long rentId) {
        return hibernateRentRepository.findOne(rentId);
    }
}
