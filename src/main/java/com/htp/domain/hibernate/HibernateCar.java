package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.domain.CarAvailability;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "rents"
})
@ToString(exclude = {
        "rents"
})
@Entity
@Table(name = "m_cars")
public class HibernateCar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "model_id")
    private Long model;

    @Column(name = "price_per_day")
    private Double pricePerDay;

    @Column(name = "office_id")
    private Long office;

    @Column(name = "availability_status")
    @Enumerated(EnumType.STRING)
    private CarAvailability availabilityStatus = CarAvailability.AVAILABLE;

    @JsonManagedReference
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<HibernateRent> rents = Collections.emptySet();
}
