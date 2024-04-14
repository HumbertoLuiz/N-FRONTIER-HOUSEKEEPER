package com.learning.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.core.exceptions.TokenBlackListException;
import com.learning.core.models.TokenBlackList;
import com.learning.core.repository.TokenBlackListRepository;

@Service
public class TokenBlackListService {

    @Autowired
    private TokenBlackListRepository repository;

    public void checkToken(String token) {
        if (repository.existsByToken(token)) { throw new TokenBlackListException(); }
    }

    public void putTokenOnBlackList(String token) {
        if (!repository.existsByToken(token)) {
            var tokenBlackList= new TokenBlackList();
            tokenBlackList.setToken(token);
            repository.save(tokenBlackList);
        }
    }

}
