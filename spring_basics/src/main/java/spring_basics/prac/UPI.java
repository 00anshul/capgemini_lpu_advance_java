package spring_basics.prac;

import org.springframework.stereotype.Component;

@Component
public class UPI implements Payment {

	@Override
	public void send() {
		System.out.println("UPI");
		
	}

}
