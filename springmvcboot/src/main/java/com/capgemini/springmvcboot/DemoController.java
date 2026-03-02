package com.capgemini.springmvcboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/create-account")
    public String register(@ModelAttribute Users user) {

        jpa.save(user);
        System.out.println(user.getContact());
        System.out.println(user.getEmail());
        System.out.println(user.getName());

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logincheck")
    public String logincheck(@ModelAttribute Users user) {

        Users validUser = jpa.findByEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        );

        if (validUser != null) {
            return "success";
        } else {
            return "login";
        }
    }
    @GetMapping("/hey")
    public ModelAndView sendData() {
        ModelAndView m = new ModelAndView();
        
        java.util.List<String> userList = java.util.Arrays.asList("Miller", "John", "Sarah", "Jane");
        
        m.addObject("messages", userList);
        
        m.setViewName("abc"); 
        return m;
    }
}