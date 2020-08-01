package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "m_location")
public class HibernateLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String house;

    @Column
    private String apartment;

    @Column(name = "is_deleted")
    private boolean deleted;

    @JsonManagedReference
    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private HibernateUser user;
}
