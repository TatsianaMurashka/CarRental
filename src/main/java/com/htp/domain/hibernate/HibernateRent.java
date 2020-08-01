package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.domain.RentStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "rent_start_date")
    private LocalDateTime rentStartDate;

    @Column(name = "rent_end_date")
    private LocalDateTime rentEndDate;

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
