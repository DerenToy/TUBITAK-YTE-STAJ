package yte.intern.springintern.components;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    //final geçiriyor.. bi de constructor
    public BeanTest beanTest(){
    return new BeanTest();


    }
}
