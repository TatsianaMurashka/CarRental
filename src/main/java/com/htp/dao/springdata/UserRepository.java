package com.htp.dao.springdata;

import com.htp.domain.hibernate.HibernateUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@CacheConfig(cacheNames = {"usersAdmins"})
public interface UserRepository extends CrudRepository<HibernateUser, Long>, JpaRepository<HibernateUser, Long>, PagingAndSortingRepository<HibernateUser, Long> {


    @Cacheable
    @Query(value = "select u from HibernateUser u join u.roles role where role.roleName = 'ROLE_ADMIN' ")
    List<HibernateUser> findUsersWithAdminRoles();

    @Query(value = "select u from HibernateUser u join u.roles role where role.roleName = :role ")
    List<HibernateUser> findUsersWithAdminRolesWithParams(@Param("role") String searchRole);

    @Query(value = "select * from m_users inner join m_roles mr on m_users.id = mr.user_id where role_name = :role", nativeQuery = true)
    List<HibernateUser> findUsersWithAdminRolesWithParamsNative(@Param("role") String searchRole);

    @Query("select u.login from HibernateUser u order by u.id")
    List<String> findAllUserNames();

    @Query("select u.phoneNumber, u.firstName from HibernateUser u order by u.id")
    List<Date> findAllBirthDate();

    @Query("select u.id, u.login, u.firstName, u.lastName from HibernateUser u order by u.id")
    List<Object[]> findAllUserProfiles();

    @Modifying
    @Query("update HibernateUser u set u.firstName = :firstName, u.lastName = :lastName, u.phoneNumber = :phoneNumber, u.login = :login")
    int updateUser(String firstName, String lastName, String phoneNumber, String login);

}
