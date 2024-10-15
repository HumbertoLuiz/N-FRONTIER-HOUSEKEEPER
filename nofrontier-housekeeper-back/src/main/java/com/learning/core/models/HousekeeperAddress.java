package com.learning.core.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "housekeeper_address")
public class HousekeeperAddress extends IdBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 60)
    private String address;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(nullable = false, length = 60)
    private String neighborhood;

    @Column(nullable = true)
    private String complement;

    @Column(name = "zip_code", nullable = false, length = 8)
    private String zipCode;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

}
