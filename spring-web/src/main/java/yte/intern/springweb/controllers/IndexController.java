package yte.intern.springweb.controllers;


import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/courses/{id}")
    public void test( @PathVariable Integer id, @RequestParam String name, @RequestParam String instructor) {
        System.out.println(id + " " + name + " " + instructor);
    }



}
