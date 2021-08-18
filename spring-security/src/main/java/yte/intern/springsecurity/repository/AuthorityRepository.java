package yte.intern.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yte.intern.springsecurity.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
