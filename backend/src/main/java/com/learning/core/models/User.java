package com.learning.core.models;

import java.time.LocalDate;
import java.util.List;

import com.learning.core.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class User {

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String completeName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 11, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(nullable = true, unique = true, length = 11)
    private String cpf;

    @Column(nullable = true)
    private LocalDate birth;

    @Column(nullable = true, length = 11)
    private String phoneNumber;

    @Column(nullable = true)
    private Double reputation;

    @Column(nullable = true, unique = true)
    private String keyPix;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "document_picture", nullable = true)
    private Picture documentPicture;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "user_picture", nullable = true)
    private Picture userPicture;

    @ManyToMany
    @JoinTable(
        name = "cities_attended_users",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "city_attended_id")
    )
    private List<CityAttended> citiesAttended;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = true)
    private HousekeeperAddress address;

    public Boolean isHousekeeper() {
        return userType.equals(UserType.HOUSEKEEPER);
    }

    public Boolean isCustomer() {
        return userType.equals(UserType.CUSTOMER);
    }
}
