package com.htp.controller.updater;

import com.htp.controller.SpringDataCarController;
import com.htp.controller.SpringDataRentController;
import com.htp.domain.CarAvailability;
import com.htp.domain.RentStatus;
import com.htp.domain.hibernate.HibernateRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RentsUpdaterAsync {
    private static volatile boolean isTerminated = false;

    public RentsUpdaterAsync() {
        System.out.println("RentsUpdater()");
    }

    @PreDestroy
    public void destroy() {
        isTerminated = true;
    }

    @Autowired
    SpringDataCarController carController;
    @Autowired
    SpringDataRentController rentController;

    @Async
    public void async() {
        while (!isTerminated) {
            System.out.println("RentsUpdaterAsync: 10 seconds async check");
            handleRentsExpirations();
            handleOldCars();
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {}
        }
    }

    private void handleRentsExpirations() {
        List<HibernateRent> rents = rentController.findAllInternal();
        LocalDateTime now = LocalDateTime.now();

        for (HibernateRent rent : rents) {
            if (Timestamp.valueOf(now).getTime() > Timestamp.valueOf(rent.getRentEndDate()).getTime()) {
                rentController.updateRentStatusInternal(rent.getId(), RentStatus.CLOSED);
            }
        }
    }

    private void handleOldCars() {
        var oldCars = carController.getCarsWithRentCountInternal(2L);
        for (var car : oldCars) {
            carController.updateCarStatus(car.getId(), CarAvailability.EXPIRED);
        }
    }
}
