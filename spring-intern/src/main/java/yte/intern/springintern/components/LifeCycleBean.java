package yte.intern.springintern.components;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class LifeCycleBean {

    public void print(){
        System.out.println("Print");

    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }

    public LifeCycleBean() {
        System.out.println("Constructor");
    }


    @PreDestroy
    public void destroy() {
        System.out.println("Destroy");
    }
}
