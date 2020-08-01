package com.htp.dao.springdata;

import com.htp.domain.CarAvailability;
import com.htp.domain.hibernate.HibernateCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<HibernateCar, Long>, JpaRepository<HibernateCar, Long>, PagingAndSortingRepository<HibernateCar, Long> {

    @Query(value = "select c from HibernateCar c where c.id in (select r.carId from HibernateRent r where r.rentStatus = 'CLOSED' group by r.carId having count(r.carId) > :count)")
    List<HibernateCar> findAllCarsWithRentCount(Long count);

    @Query(value = "select c from HibernateCar c where c.availabilityStatus = 'AVAILABLE' ")
    List<HibernateCar> findAllAvailableCars();

    @Modifying
    @Query("update HibernateCar c set c.pricePerDay = :price, c.registrationNumber = :registrationNumber, c.office = :officeId, c.model = :modelId where c.id = :id")
    int updateCar(Long id, Double price, String registrationNumber, Long officeId, Long modelId);

    @Modifying
    @Query("update HibernateCar c set c.availabilityStatus = :availability where c.id = :id")
    int updateCarAvailability(Long id, CarAvailability availability);

}
