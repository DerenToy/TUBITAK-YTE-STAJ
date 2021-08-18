package yte.intern.springintern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import yte.intern.springintern.components.DependencyInjectionTest;
import yte.intern.springintern.components.InjectionTest;
import yte.intern.springintern.components.IocTest;
import yte.intern.springintern.components.LifeCycleBean;

@SpringBootApplication
public class SpringInternApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringInternApplication.class, args);
		/*
		DependencyInjectionTest dependencyInjectionTest = context.getBean(DependencyInjectionTest.class);  //application context dönüyor
		//benden böyle bir class istedin ama benim container'ımda böyle bir class yok
		//demiş oluyor.. bu da @Component'ten kaynaklı
		dependencyInjectionTest.printAll();
*/
		/*
		LifeCycleBean lifeCycleBean = context.getBean(LifeCycleBean.class);
		lifeCycleBean.print();
        */

		// woow.. lifecycle daki constructor ve postconstruct ve destroy çağırmadan geldi.. çünkü beanleri oluşuturuyormuş hep.
		InjectionTest injectionTest = context.getBean(InjectionTest.class);
		injectionTest.print();

	}

}
