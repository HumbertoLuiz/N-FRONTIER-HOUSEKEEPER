// package com.learning.core.services;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.learning.core.exceptions.TokenNaBlackListException;
// import com.learning.core.repository.TokenBlackListRepository;

// @Service
// public class TokenBlackListService {

//    @Autowired
//    private TokenBlackListRepository repository;

//    public void checkToken(String token) {
//        if (repository.existsByToken(token)) {
//            throw new TokenNaBlackListException();
//        }
//    }

//    public void colocarTokenNaBlackList(String token) {
//        if (!repository.existsByToken(token)) {
//            var tokenBlackList = new TokenBlackList();
//            tokenBlackList.setToken(token);
//            repository.save(tokenBlackList);
//        }
//    }

// }
