package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

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
