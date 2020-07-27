package com.htp.dao.springdata;

import com.htp.domain.RentStatus;
import com.htp.domain.hibernate.HibernateRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RentRepository extends CrudRepository<HibernateRent, Long>, JpaRepository<HibernateRent, Long>, PagingAndSortingRepository<HibernateRent, Long> {

    @Modifying
    @Query("update HibernateRent r set r.rentStatus = :rentStatus where r.id = :id")
    int updateRent(Long id, RentStatus rentStatus);
}
