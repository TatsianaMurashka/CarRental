package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@CacheConfig(cacheNames = {"activeUsers"})
public interface UserRepository extends CrudRepository<HibernateUser, Long>, JpaRepository<HibernateUser, Long>, PagingAndSortingRepository<HibernateUser, Long> {

    @Query(value = "select u from HibernateUser u join u.roles role where role.roleName = 'ROLE_USER' and u.deleted = false")
    List<HibernateUser> findUsersWithUserRoles();

    @Cacheable
    @Query("select u from HibernateUser u where u.deleted = false")
    List<HibernateUser> findAllActiveUsers();

    @Query("select u from HibernateUser u where u.login = :login")
    List<HibernateUser> findUsersByLogin(String login);

    @Modifying
    @Query("update HibernateUser u set u.firstName = :firstName, u.lastName = :lastName, u.phoneNumber = :phoneNumber, u.login = :login, u.password = :password where u.id = :id")
    int updateUser(Long id, String firstName, String lastName, String phoneNumber, String login, String password);

    @Modifying
    @Query("update HibernateUser u set u.deleted = 'true' where u.id = :id")
    int deleteUser(Long id);

}
