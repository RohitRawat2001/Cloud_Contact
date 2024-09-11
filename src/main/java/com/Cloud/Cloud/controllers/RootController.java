package com.Cloud.Cloud.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Cloud.Cloud.entities.User;
import com.Cloud.Cloud.helpers.Helper;
import com.Cloud.Cloud.services.UserService;

// ya saara url ka liya chalagha
@ControllerAdvice
public class RootController {

    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    private void addLoggedInUserInformation(Model model, Authentication authentication) {
        if(authentication == null){
            return;
        }
        // es application mein username hi email hai
        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User logged in : {}", username);

        User user = userService.getUserByEmail(username);

        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());

        model.addAttribute("loggedInUser", user);

    }

}
