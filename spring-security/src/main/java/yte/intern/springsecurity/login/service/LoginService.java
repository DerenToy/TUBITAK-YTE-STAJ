package yte.intern.springsecurity.login.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.springsecurity.login.request.LoginRequest;
import yte.intern.springsecurity.login.util.JwtUtil;

import javax.validation.Valid;

@RestController
public class LoginService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    private final AuthenticationManager authenticationManager;

    public LoginService(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }



    public String login(@Valid @RequestBody final LoginRequest loginRequest){

        var token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());


        try{
            Authentication authenticationToken = authenticationManager.authenticate(token);
            String jwt = JwtUtil.generateToken(authenticationToken, secretKey);
            return jwt;

        }catch (AuthenticationException ex){

        }

        return null;
    }






}

