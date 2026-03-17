package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page Controller");

        // Sending data to the view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("YouTubeChannel", "Learn Code with me");
        model.addAttribute("githubRepo", "https://github.com/pr8986");
        return "home";
    }

    // About route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About Page Loading");
        return "about";
    }

    // services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services Page Loading");
        return "services";
    }

    // Contact Page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // Login Page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    // Register Page
    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        // default data bhi daal skte hai
        // userForm.setName("Prabhaakr Kumar");
        // userForm.setAbout("This is about : Write something about yourself.");
        model.addAttribute("userForm", userForm);

        return "register";
    }

    // processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,HttpSession session) {
        System.out.println("Processing Register Form");

        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
        
        // TODO:Validate userForm [next video]

        // save to database

        // userservice

        // UserForm -> User
        // User user = User.builder()
        //         .name(userForm.getName())
        //         .email(userForm.getEmail())
        //         .password(userForm.getPassword())
        //         .about(userForm.getAbout())
        //         .phoneNumber(userForm.getPhoneNumber())
        //         .profilePic(
        //                 "https://www.dreamstime.com/default-profile-picture-icon-high-resolution-high-resolution-default-profile-picture-icon-symbolizing-no-display-picture-image360167031")
        //         .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://www.dreamstime.com/default-profile-picture-icon-high-resolution-high-resolution-default-profile-picture-icon-symbolizing-no-display-picture-image360167031");


        User savedUser = userService.saveUser(user);

        System.out.println("Saved User: " ); 
        // message="Registration successful"

        //add the message

        Message message = Message.builder().content("Registration successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        // redirect to login page
        return "redirect:/register";
    }
}
