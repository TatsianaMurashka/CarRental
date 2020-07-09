package com.htp.domain.hibernate;

import com.htp.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HibernateUser implements Serializable {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String passportData;

    private String login;

    private String password;

    private Timestamp created;

    private Timestamp changed;

    private Long locationId;

    private boolean deleted;

    private Gender gender = Gender.NOT_SELECTED;
}
