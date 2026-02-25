package spring_basics.prac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import spring_basics.Config;

@Component
public class User {
	@Autowired
	private Payment payment;

	public void display() {
		payment.send();
	}

	public static void main(String[] args) {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(Config.class);
		User u = ioc.getBean(User.class);
		System.out.println(u);
		u.display();
	}
}
