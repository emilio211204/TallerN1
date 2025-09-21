package com.desarrollo.spring.complexivo.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String getFormAuth(Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "auth/login";
    }

    @GetMapping("/")
    public String reservas(){
        return "reservas/lista";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.logout(); //Cerrar sesión
        response.sendRedirect("/login?logout");
        return null;
    }
}
