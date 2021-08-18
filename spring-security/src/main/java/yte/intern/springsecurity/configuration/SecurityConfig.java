package yte.intern.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import yte.intern.springsecurity.service.CustomAuthenticationProvider;
import yte.intern.springsecurity.service.CustomUserDetailsService;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration // bean aslında bu class'da
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//diğer taraftakini aldık
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final JwtRequestFilter jwtRequestFilter;
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationProvider customAuthenticationProvider, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    // CTRL+o ile override yapılcak metodları açabiliyoruz
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //PasswordEncoder encoder =  passwordEncoder(); // shift F6


        //auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);
        /*
		auth.inMemoryAuthentication()
				.withUser("user") // kullanıcı ekliyor
				.password(encoder.encode("user"))
                .authorities("USER") // yetki
				.and()
  			    .withUser("admin")
                .password(encoder.encode("admin")) //bunu vermezsek patlıyor
                //.roles("ADMIN")
				.authorities("ADMIN")
				.and()
				.passwordEncoder(encoder);


         */
// .roles("ADMIN") -> authotirty READ_ADMIN_PAGE falan gibi yazılabiliyor
    }

    /*
    @Bean
    public PasswordEncoder passwordEncoder(){
        //return new BCryptPasswordEncoder();
    return NoOpPasswordEncoder.getInstance(); //böyle kullanma tabiki
    }
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
               // .antMatchers("/user").hasAnyAuthority("USER", "ADMIN") //iki parametre geçerken
                //.antMatchers("/admin").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable()
                .csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();


    }

}
