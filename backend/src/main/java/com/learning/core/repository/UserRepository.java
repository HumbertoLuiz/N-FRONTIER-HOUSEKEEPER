package com.learning.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.core.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    @Query(
        """
        SELECT
            AVG(u.reputation)
        FROM
            User u
        WHERE
            u.UserType = com.learning.core.enums.UserType.HOUSEKEEPER
        """
    )

    default Boolean isEmailAlreadyRegistered(User user) {
        if (user.getEmail() == null) {
            return false;
        }

        return findByEmail(user.getEmail())
            .map(userFound -> !userFound.getId().equals(user.getId()))
            .orElse(false);
    }
    
    boolean existsByEmail(String email);
}
