package com.Cloud.Cloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Cloud.Cloud.entities.User;
import com.Cloud.Cloud.helpers.Message;
import com.Cloud.Cloud.helpers.MessageType;
import com.Cloud.Cloud.helpers.ResourceNotFoundException;
import com.Cloud.Cloud.respositorys.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    // verfying email
    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token,HttpSession session) {

       User user = userRepo.findByEmailToken(token).orElse(null);

       if (user != null) {
        // user fetch hua hai :: process karna hai

        if (user.getEmailToken().equals(token)) {
            user.setEmailVerified(true);
            user.setEnabled(true);
            userRepo.save(user);
            session.setAttribute("message", Message.builder()
                    .type(MessageType.green)
                    .content("You email is verified. Now you can login  ")
                    .build());
            return "success_page";
        }

        session.setAttribute("message", Message.builder()
                .type(MessageType.red)
                .content("Email not verified ! Token is not associated with user .")
                .build());
        return "error_page";

    }

    session.setAttribute("message", Message.builder()
            .type(MessageType.red)
            .content("Email not verified ! Token is not associated with user .")
            .build());

    return "error_page";
}

}