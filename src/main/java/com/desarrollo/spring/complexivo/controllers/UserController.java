package com.desarrollo.spring.complexivo.controllers;
import com.desarrollo.spring.complexivo.models.User;
import com.desarrollo.spring.complexivo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/register")
    public String getFormRegister(@ModelAttribute User user, Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public RedirectView registerUser(@ModelAttribute User user){
        try {
            this.service.save(user);
            return new RedirectView("/login");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return new RedirectView("/register?duplicate");
        }
    }

}