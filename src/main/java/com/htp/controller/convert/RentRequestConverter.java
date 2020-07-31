package com.htp.controller.convert;

import com.htp.controller.request.RentCreateRequest;
import com.htp.domain.hibernate.HibernateRent;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.Date;

@Slf4j
public abstract class RentRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateRent doConvert(HibernateRent rent, RentCreateRequest request) {

        rent.setCarId(request.getCarId());
        rent.setUserId(request.getUserId());
        rent.setRentStartDate(request.getRentStartDate());
        rent.setRentEndDate(request.getRentEndDate());
        rent.setRentPrice(request.getRentPrice());
        rent.setChanged(new Timestamp(new Date().getTime()));

        return rent;
    }
}
