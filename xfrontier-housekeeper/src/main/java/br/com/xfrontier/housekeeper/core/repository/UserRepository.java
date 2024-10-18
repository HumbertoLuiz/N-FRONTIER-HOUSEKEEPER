package br.com.xfrontier.housekeeper.core.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.xfrontier.housekeeper.core.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByCpf(String cpf);

    Optional<User> findByKeyPix(String keyPix);

    Page<User> findByCitiesAttendedIbgeCode(String ibgeCode, Pageable pageable);

    Boolean existsByCitiesAttendedIbgeCode(String ibgeCode);

    @Query("""
        SELECT
            AVG(u.reputation)
        FROM
            User u
        WHERE
            u.userType = br.com.xfrontier.housekeeper.core.enums.UserType.HOUSEKEEPER
        """)
    Double getHousekeeperAverageReputation();

    default Boolean isEmailAlreadyRegistered(User user) {
        if (user.getEmail() == null) { return false; }
        return findByEmail(user.getEmail())
            .map(userFound -> !userFound.getId().equals(user.getId()))
            .orElse(false);
    }

    default Boolean isCpfAlreadyRegistered(User user) {
        if (user.getCpf() == null) { return false; }
        return findByCpf(user.getCpf())
            .map(userFound -> !userFound.getId().equals(user.getId()))
            .orElse(false);
    }

    default Boolean isKeyPixAlreadyRegistered(User user) {
        if (user.getKeyPix() == null) { return false; }
        return findByKeyPix(user.getKeyPix())
            .map(userFound -> !userFound.getId().equals(user.getId()))
            .orElse(false);
    }

    boolean existsByEmail(String email);

}
