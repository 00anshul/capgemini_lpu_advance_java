package spring_basics.prac;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CreditCard implements Payment {

	@Override
	public void send() {
		System.out.println("CreditCArd");
		
	}

}
