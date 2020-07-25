package com.htp.dao.hibernate;

import com.htp.dao.HibernateRentDao;
import com.htp.domain.hibernate.HibernateRent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("rentHibernateRepository")
public class RentHibernateRepository implements HibernateRentDao {

    private SessionFactory sessionFactory;

    public RentHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<HibernateRent> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select rent from HibernateRent rent order by rent.id asc", HibernateRent.class).list();
        }
    }

    @Override
    public Optional<HibernateRent> findById(Long rentId) {
        return Optional.empty();
    }

    @Override
    public HibernateRent findOne(Long rentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateRent.class, rentId);
        }
    }

    @Override
    public List<HibernateRent> search(String searchParam) {
        return null;
    }

    @Override
    public HibernateRent save(HibernateRent rent) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(rent);
            return rent;
        }
    }

    @Override
    public HibernateRent update(HibernateRent rent) {
        return null;
    }

    @Override
    public int delete(Long rentId) {
        return 0;
    }
}
