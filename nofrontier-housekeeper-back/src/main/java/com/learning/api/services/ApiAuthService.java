package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.learning.api.dtos.requests.RefreshRequest;
import com.learning.api.dtos.requests.TokenRequest;
import com.learning.api.dtos.responses.TokenResponse;
import com.learning.core.services.TokenBlackListService;
import com.learning.core.services.token.adapters.TokenService;

@Service
public class ApiAuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenBlackListService tokenBlackListService;

    public TokenResponse authenticate(TokenRequest tokenRequest) {
        var email= tokenRequest.getEmail();
        var password= tokenRequest.getPassword();

        var authentication= new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authentication);

        var access= tokenService.generateAccessToken(email);
        var refresh= tokenService.generateRefreshToken(email);

        return new TokenResponse(access, refresh);
    }

    public TokenResponse reauthenticate(RefreshRequest refreshRequest) {

        var token= refreshRequest.getRefresh();
        tokenBlackListService.checkToken(token);

        var email= tokenService.getSubjectDoRefreshToken(token);
        userDetailsService.loadUserByUsername(email);

        var access= tokenService.generateAccessToken(email);
        var refresh= tokenService.generateRefreshToken(email);

        tokenBlackListService.putTokenOnBlackList(token);

        return new TokenResponse(access, refresh);
    }

    public void logout(RefreshRequest refreshRequest) {
        var token= refreshRequest.getRefresh();
        tokenBlackListService.putTokenOnBlackList(token);
    }
}
