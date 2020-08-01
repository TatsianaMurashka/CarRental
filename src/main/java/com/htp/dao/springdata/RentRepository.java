package com.htp.dao.springdata;

import com.htp.domain.RentStatus;
import com.htp.domain.hibernate.HibernateRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RentRepository extends CrudRepository<HibernateRent, Long>, JpaRepository<HibernateRent, Long>, PagingAndSortingRepository<HibernateRent, Long> {

    @Modifying
    @Query("update HibernateRent r set r.rentStatus = :rentStatus where r.id = :id")
    int updateRentStatus(Long id, RentStatus rentStatus);

    @Modifying
    @Query("update HibernateRent r set r.rentPrice = :rentPrice, r.carId = :carId, r.userId = :userId, r.rentStartDate = :startDate, r.rentEndDate = :endDate, r.changed = :changed  where r.id = :id")
    int updateRent(Long id, Double rentPrice, Long carId, Long userId, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime changed);

    @Query(value = "select r from HibernateRent r where r.rentStatus = :rentStatus ")
    List<HibernateRent> findRentsWithStatus(RentStatus rentStatus);
}
