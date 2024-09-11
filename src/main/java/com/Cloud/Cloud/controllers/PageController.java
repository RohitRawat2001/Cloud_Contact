package com.Cloud.Cloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Cloud.Cloud.entities.User;
import com.Cloud.Cloud.forms.UserForm;
import com.Cloud.Cloud.helpers.Message;
import com.Cloud.Cloud.helpers.MessageType;
import com.Cloud.Cloud.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;




@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    


    @RequestMapping("/home")
    public String home(){
        return "home";
    } 
    
    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        return "services";
    }

    @GetMapping("/login")
    public String login(){
        return new String("login");
    }   
    
    @GetMapping("/contact")
    public String contact(){
        return new String("contact");
    }   
    
    @GetMapping("/register")
    public String register(Model model){
      UserForm userForm = new UserForm();
      model.addAttribute("userForm", userForm);
      return "register";

    }

    //processing of register
    @RequestMapping(value = "/do-register" ,method = RequestMethod.POST)   // BIndingResult for error hai ya nahi
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session){
      System.out.println("Processing fof resigter");
      //fetch form data
      System.out.println(userForm);
    
      //validate form data

      // Error hai tho ya chalaga warna nicha wala saara
      if(rBindingResult.hasErrors()){
        return "register";
      }

      //save to database   // userservice
     // UserFrom ka use kr ka user banaya or userFrom ka data bhaj diya user mein
    //   User user =User.builder()
    //   .name(userForm.getName())
    //   .email(userForm.getEmail())
    //   .password(userForm.getPassword())
    //   .about(userForm.getAbout())
    //   .PhoneNumber(userForm.getPhoneNumber())
    //    .ProfilePic("https://w7.pngwing.com/pngs/205/731/png-transparent-default-avatar-thumbnail.png")
    //   .build();

    // builder ki maadat sa default values nahi aari thi esliya humna ya use kiya uski jagha
    User user = new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setEnabled(false);
    user.setProfilePic("https://w7.pngwing.com/pngs/205/731/png-transparent-default-avatar-thumbnail.png");

      User savedUser = userService.saveUser(user);
      System.out.println("user saved-----------------");
      // message:"Register successfully"
      Message message = Message.builder().content("Registeration Successfull").type(MessageType.green).build();
      session.setAttribute("message",message);

      //redirect to login page
      return "redirect:/register";
    }

}
