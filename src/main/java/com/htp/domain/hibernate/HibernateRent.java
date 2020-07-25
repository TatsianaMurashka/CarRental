package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.domain.RentStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "user"
})
@ToString(exclude = {
        "user"
})
@Entity
@Table(name = "m_rent")
public class HibernateRent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "rent_start_date")
    private Date rentStartDate;

    @Column(name = "rent_end_date")
    private Date rentEndDate;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "rent_price")
    private Double rentPrice;

    @Column(name = "rent_status")
    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus = RentStatus.NEW;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private HibernateUser user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false, insertable = false, updatable = false) //Investigate this!!
    private HibernateCar car;
}
