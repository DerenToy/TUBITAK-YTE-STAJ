package yte.intern.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsServiceImpl userDetailsService;


    public UserAuthenticationProvider(final UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;

    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username =(String) authentication.getPrincipal(); //casting yaptık bu obje dönüyo normalde
        String password = (String) authentication.getCredentials(); //password bilgisi burda
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Şifre hatalı");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) { // anlatacakmış
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
