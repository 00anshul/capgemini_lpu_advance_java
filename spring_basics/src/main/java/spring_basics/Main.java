package spring_basics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
public static void main(String[] args) {
//	Person p = new Person();
	
//	ApplicationContext ioc = new ClassPathXmlApplicationContext("config.xml");
//	Person person = ioc.getBean("person",Person.class);
//	
//	person.meassage();
//	
//	Employee e = ioc.getBean("employee",Employee.class);
//	System.out.println(e);
	
	ApplicationContext ioc =  new AnnotationConfigApplicationContext(Config.class);
	
//	Employee e = ioc.getBean(Employee.class);
//	System.out.println(e);
	
	Person p = ioc.getBean(Person.class);
	
	System.out.println(p.getM());
	
	System.out.println(p.getScan());
}

}
