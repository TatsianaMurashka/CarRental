package com.htp.dao.hibernate;

import com.htp.dao.HibernateCarDao;
import com.htp.domain.hibernate.HibernateCar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository("carHibernateRepository")
public class CarHibernateRepository implements HibernateCarDao {

    private SessionFactory sessionFactory;

    private EntityManagerFactory entityManagerFactory;

    public CarHibernateRepository(SessionFactory sessionFactory, EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = sessionFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<HibernateCar> findAll() {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery("select car from HibernateCar car order by car.id asc", HibernateCar.class).list();
//        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select car from HibernateCar car order by car.id asc", HibernateCar.class).getResultList();
    }

    @Override
    public Optional<HibernateCar> findById(Long carId) {
        return Optional.empty();
    }

    @Override
    public HibernateCar findOne(Long carId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateCar.class, carId);
        }
    }

    @Override
    public List<HibernateCar> search(String searchParam) {
        return null;
    }

    @Override
    public HibernateCar save(HibernateCar car) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(car);
            return car;
        }
    }

    @Override
    public HibernateCar update(HibernateCar car) {
        return save(car);
    }

    @Override
    public int delete(Long carId) {
        return 0;
    }
}
