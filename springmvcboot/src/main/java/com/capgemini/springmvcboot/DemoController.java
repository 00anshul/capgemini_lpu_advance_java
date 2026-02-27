package com.capgemini.springmvcboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

	@Autowired
	JpaRepo jpa;
	
	@GetMapping("/hello")
	public String getHi() {
		return "welcome";
	}
	
	@GetMapping("/register")
	public String createAccount() {
		return "register";
	}
	
//	@PostMapping("/create-account")
//	public String register (HttpServletRequest request) {
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
//		String contact = request.getParameter("contact");
//		
//		System.out.println(name);
//		System.out.println(email);
//		System.out.println(contact);
//
//		return "done";
//
//	}
	
	@PostMapping("/create-account")
	public String register (@ModelAttribute Users user) {
		
		jpa.save(user); 
		System.out.println(user.getContact());
		System.out.println(user.getEmail());
		System.out.println(user.getName());

		return "done";

	}
	
	
	
}
