package yte.intern.springsecurity.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

        @GetMapping("/user")
        public String user() {
            return "Ben user sayfasıyım!";
        }

        @PreAuthorize("hasAuthority('ADMIN')")
        @GetMapping("/admin")
        public String admin() {
            return "Ben admin sayfasıyım";
        }


}
