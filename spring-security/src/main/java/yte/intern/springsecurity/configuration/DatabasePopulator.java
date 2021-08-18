package yte.intern.springsecurity.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yte.intern.springsecurity.entity.Authority;
import yte.intern.springsecurity.entity.Users;
import yte.intern.springsecurity.repository.AuthorityRepository;
import yte.intern.springsecurity.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabasePopulator {

    private final AuthorityRepository authorityRepository;
    private final UserRepository customUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabasePopulator(final AuthorityRepository authorityRepository,
                             final UserRepository customUserRepository,
                             final PasswordEncoder passwordEncoder) {
        this.authorityRepository = authorityRepository;
        this.customUserRepository = customUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void populateDatabase() {
        Authority userAuthority = authorityRepository.save(new Authority(null, "USER", new HashSet<>()));
        Authority adminAuthority = authorityRepository.save(new Authority(null, "ADMIN", new HashSet<>()));

        Users admin = new Users(null, "admin", passwordEncoder.encode("admin"), Set.of(userAuthority, adminAuthority));
        customUserRepository.save(admin);

        Users user = new Users(null, "user", passwordEncoder.encode("user"), Set.of(userAuthority));
        customUserRepository.save(user);

        System.out.println(customUserRepository.findByUsername("admin").get().getPassword());
    }
}