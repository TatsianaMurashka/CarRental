package com.htp.controller.convert;

import com.htp.controller.request.UserCreateRequest;
import com.htp.domain.Roles;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

@Component
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, HibernateUser> {

    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser user = new HibernateUser();
        user.setPassportData(request.getPassportData());
        user.setLocation(1L);
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));

        HibernateRole hibernateRole = new HibernateRole();
        hibernateRole.setRoleName(Roles.ROLE_USER.name());
        hibernateRole.setUser(user);
        user.setRoles(Collections.singleton(hibernateRole));

        return doConvert(user, request);
    }
}
