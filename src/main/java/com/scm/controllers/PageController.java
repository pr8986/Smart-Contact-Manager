package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page Controller");

        //Sending data to the view
        model.addAttribute("name","Substring Technologies" );
        model.addAttribute("YouTubeChannel", "Learn Code with me");
        model.addAttribute("githubRepo","https://github.com/pr8986" );
        return "home"; 
    }

    //About route
    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", true);
        System.out.println("About Page Loading");
        return "about";
    }

    //services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services Page Loading");
        return "services";
    }

    //Contact Page
    @GetMapping("/contact")
    public String contact(){
        return new String("contact");
    }

    //Login Page
    @GetMapping("/login")
    public String login(){
        return new String("login");
    }

    //Register Page
    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
