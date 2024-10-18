package br.com.xfrontier.housekeeper.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.housekeeper.core.models.CityAttended;

public interface CityAttendedRepository extends JpaRepository<CityAttended, Long> {

    Optional<CityAttended> findByIbgeCode(String ibgeCode);

}
