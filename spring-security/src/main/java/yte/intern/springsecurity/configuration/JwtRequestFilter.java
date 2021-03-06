package yte.intern.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yte.intern.springsecurity.login.util.JwtUtil;
import yte.intern.springsecurity.service.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    private final UserDetailsServiceImpl userDetailsService;

    public JwtRequestFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader =request.getHeader("Authorization");
        if(authenticationHeader != null && authenticationHeader.startsWith("Bearer")){
            String jwtToken  = authenticationHeader.substring(7);
            // extract user yapmalı kullanıcıya bakmalı var mı
            String username = JwtUtil.extractUsername(jwtToken, secretKey);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // user var mı falan bakma işi ?

            //authentice olan kullanıcının bilgilerini security context holdera biz koyucaz

            if(SecurityContextHolder.getContext().getAuthentication() == null){

                var token = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }


        }
        filterChain.doFilter(request,response);
    }
}
