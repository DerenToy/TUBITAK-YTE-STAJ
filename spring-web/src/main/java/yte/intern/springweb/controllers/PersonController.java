package yte.intern.springweb.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yte.intern.springweb.dto.Person;

import java.util.List;

@RestController
public class PersonController {


    @PostMapping("/person")
    public List<Person> person(@RequestBody List<Person> personList) {
        for (Person person : personList) {
            person.setAge(person.getAge() + 1);
        }

        return personList;


    }

}
