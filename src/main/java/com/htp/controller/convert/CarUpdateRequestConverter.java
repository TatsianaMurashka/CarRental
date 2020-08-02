package com.htp.controller.convert;

import com.htp.controller.request.CarUpdateRequest;
import com.htp.domain.hibernate.HibernateCar;
import com.htp.exeptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CarUpdateRequestConverter extends CarRequestConverter<CarUpdateRequest, HibernateCar>{
    @Override
    public HibernateCar convert(CarUpdateRequest request) {
        HibernateCar hibernateCar = ofNullable(entityManager.find(HibernateCar.class, request.getId())).orElseThrow(ResourceNotFoundException::new);
        hibernateCar.setId(request.getId());
        return doConvert(hibernateCar, request);
    }
}
