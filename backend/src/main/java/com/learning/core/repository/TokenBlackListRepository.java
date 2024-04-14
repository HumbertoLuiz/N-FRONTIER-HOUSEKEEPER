package com.learning.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.core.models.TokenBlackList;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

    boolean existsByToken(String token);

}
