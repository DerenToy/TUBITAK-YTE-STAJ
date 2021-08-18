package yte.intern.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yte.intern.springdata.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    List<User> findByName(String name); //direct matching
}
