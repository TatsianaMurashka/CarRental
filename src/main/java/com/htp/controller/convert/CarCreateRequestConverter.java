package com.htp.controller.convert;

import com.htp.controller.request.CarCreateRequest;
import com.htp.domain.hibernate.HibernateCar;
import org.springframework.stereotype.Component;

@Component
public class CarCreateRequestConverter extends CarRequestConverter<CarCreateRequest, HibernateCar> {
    @Override
    public HibernateCar convert(CarCreateRequest carCreateRequest) {

        HibernateCar car = new HibernateCar();

        return doConvert(car, carCreateRequest);
    }
}
