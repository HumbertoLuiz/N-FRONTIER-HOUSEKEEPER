package br.com.xfrontier.housekeeper.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.housekeeper.core.models.TokenBlackList;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

    boolean existsByToken(String token);

}
