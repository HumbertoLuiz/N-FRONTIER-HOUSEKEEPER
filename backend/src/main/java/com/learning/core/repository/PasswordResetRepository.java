package com.learning.core.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.core.models.PasswordReset;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {

    Optional<PasswordReset> findByEmail(String email);

    Optional<PasswordReset> findByToken(String token);

}
