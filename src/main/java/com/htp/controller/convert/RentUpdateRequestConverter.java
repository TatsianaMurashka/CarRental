package com.htp.controller.convert;

import com.htp.controller.request.RentUpdateRequest;
import com.htp.domain.hibernate.HibernateRent;
import com.htp.exeptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class RentUpdateRequestConverter extends RentRequestConverter<RentUpdateRequest, HibernateRent>{

    @Override
    public HibernateRent convert(RentUpdateRequest request) {

        HibernateRent hibernateRent = ofNullable(entityManager.find(HibernateRent.class, request.getId())).orElseThrow(ResourceNotFoundException::new);
        return doConvert(hibernateRent, request);
    }
}
