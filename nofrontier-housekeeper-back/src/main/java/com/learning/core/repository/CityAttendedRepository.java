package com.learning.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.core.models.CityAttended;

public interface CityAttendedRepository extends JpaRepository<CityAttended, Long> {

    Optional<CityAttended> findByIbgeCode(String ibgeCode);

}
