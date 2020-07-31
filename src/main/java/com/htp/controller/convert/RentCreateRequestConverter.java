package com.htp.controller.convert;

import com.htp.controller.request.RentCreateRequest;
import com.htp.domain.hibernate.HibernateRent;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class RentCreateRequestConverter extends RentRequestConverter<RentCreateRequest, HibernateRent>{

    @Override
    public HibernateRent convert(RentCreateRequest request) {

        HibernateRent rent = new HibernateRent();

        rent.setCreated(new Timestamp(new Date().getTime()));
        rent.setChanged(new Timestamp(new Date().getTime()));

        return doConvert(rent, request);
    }
}
