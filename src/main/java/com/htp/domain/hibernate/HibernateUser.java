package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.domain.Gender;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
        "roles", "rents"
})
@ToString(exclude = {
        "roles", "rents"
})
@Entity
@Table(name = "m_users")
public class HibernateUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "passport_data")
    private String passportData;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private boolean deleted;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<HibernateRole> roles = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<HibernateRent> rents = Collections.emptySet();

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    private HibernateLocation location;

}
