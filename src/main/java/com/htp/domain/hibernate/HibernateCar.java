package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.domain.CarAvailability;
import lombok.*;

import javax.persistence.*;
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

//    @JsonManagedReference
//    @ManyToOne
//    @JoinColumn(name = "model_id", nullable = false)
//    private HibernateModel model;
//
//    @JsonManagedReference
//    @ManyToOne
//    @JoinColumn(name = "office_id", nullable = false)
//    private HibernateOffice office;
}
