package com.htp.controller.convert;

import com.htp.controller.request.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUser;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.Date;

@Slf4j
public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    protected HibernateUser doConvert(HibernateUser user, UserCreateRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setChanged(new Timestamp(new Date().getTime()));

        return user;
    }
}
