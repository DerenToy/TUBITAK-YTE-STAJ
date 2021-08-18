package yte.intern.springintern.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DependencyInjectionTest {

    // Field Injection
    @Autowired
    private FieldInjectionBean fieldInjectionBean;

    private SetterInjectionBean setterInjectionBean;

    // Setter Injection
    @Autowired
    public void setSetterInjectionBean(SetterInjectionBean setterInjectionBean) {
        this.setterInjectionBean = setterInjectionBean;
    }

    //Constructor Injection
    private ConstructorInjectionBean constructorInjectionBean;

    @Autowired
    public DependencyInjectionTest(ConstructorInjectionBean constructorInjectionBean) {
        this.constructorInjectionBean = constructorInjectionBean;
    }


    public void printAll(){
        fieldInjectionBean.print();
        setterInjectionBean.print();
        constructorInjectionBean.print();

    }
}
