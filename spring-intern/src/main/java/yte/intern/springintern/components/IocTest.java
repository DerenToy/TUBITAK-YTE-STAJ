package yte.intern.springintern.components;


import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component  //springin yönettiği bir bean oldu
public class IocTest {
    public void test() {
        System.out.println("welcome");
    }
}
