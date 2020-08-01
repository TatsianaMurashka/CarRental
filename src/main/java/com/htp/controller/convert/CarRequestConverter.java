package com.htp.controller.convert;

import com.htp.controller.request.CarCreateRequest;
import com.htp.domain.hibernate.HibernateCar;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CarRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateCar doConvert(HibernateCar car, CarCreateRequest request) {

        car.setRegistrationNumber(request.getRegistrationNumber());
        car.setModel(request.getModelId());
        car.setPricePerDay(request.getPricePerDay());
        car.setOffice(request.getOfficeId());

        return car;
    }
}
