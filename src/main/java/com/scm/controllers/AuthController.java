package com.scm.controllers;

import com.scm.services.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.repositories.UserRepo;

@Controller
@RequestMapping("/auth")
public class AuthController {

    //verify email

   // private final UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepo userRepo;

    // AuthController(UserServiceImpl userServiceImpl) {
    //     this.userServiceImpl = userServiceImpl;
    // }

    @GetMapping("/verify-email")
    public String verifyEmail(
        @RequestParam("token") String token,HttpSession session)
    {
        User user=userRepo.findByEmailToken(token).orElse(null);  

        if(user != null){
            //user fetch hua hai :: process krna hai

            if(user.getEmailToken().equals(token)){

                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);

                session.setAttribute("message", Message.builder()
                .type(MessageType.green)
                .content("Email is  verified ! Now you can login .")
                .build());

                 return "success_page";
            }

            session.setAttribute("message", Message.builder()
            .type(MessageType.red)
            .content("Email is not verified ! Token is not assosciated with user .")
            .build());

           return "error_page";
        }

        session.setAttribute("message", Message.builder()
        .type(MessageType.red)
        .content("Email is not verified ! Token is not assosciated with user .")
        .build());

        return "error_page";
    }
}
