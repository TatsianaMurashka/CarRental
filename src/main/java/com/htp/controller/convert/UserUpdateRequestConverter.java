package com.htp.controller.convert;

import com.htp.controller.request.UserUpdateRequest;
import com.htp.domain.hibernate.HibernateUser;
import com.htp.exeptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

import static java.util.Optional.ofNullable;

@Component
public class UserUpdateRequestConverter extends UserRequestConverter<UserUpdateRequest, HibernateUser> {

    @Override
    public HibernateUser convert(UserUpdateRequest request) {

        HibernateUser hibernateUser = ofNullable(entityManager.find(HibernateUser.class, request.getId())).orElseThrow(ResourceNotFoundException::new);
        hibernateUser.setFirstName(request.getFirstName());
        hibernateUser.setLastName(request.getLastName());
        hibernateUser.setPhoneNumber(request.getPhoneNumber());
        hibernateUser.setLogin(request.getLogin());
        hibernateUser.setChanged(new Timestamp(new Date().getTime()));

        return hibernateUser;
    }
}
