package com.htp.dao.hibernate;

import com.htp.dao.HibernateUserDao;
import com.htp.domain.hibernate.HibernateUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userHibernateRepository")
public class UserHibernateRepository implements HibernateUserDao {

    private SessionFactory sessionFactory;

    public UserHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<HibernateUser> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select hu from HibernateUser hu order by id asc", HibernateUser.class).list();
        }
    }

    @Override
    public Optional<HibernateUser> findById(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<HibernateUser> findByLogin(String username) {
        return Optional.empty();
    }

    @Override
    public HibernateUser findOne(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateUser.class, userId);
        }
    }

    @Override
    public List<HibernateUser> search(String searchParam) {
        return null;
    }

    @Override
    public HibernateUser save(HibernateUser user) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(user);
            return user;
        }
    }

    @Override
    public HibernateUser update(HibernateUser user) {
        return save(user);
    }

    @Override
    public int delete(Long userId) {
        return 0;
    }
}
