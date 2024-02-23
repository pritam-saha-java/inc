package com.incallup.backend.controller;

import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.AdminDTO;
import com.incallup.backend.domain.LoginResponse;
import com.incallup.backend.service.impl.AuthenticationService;
import com.incallup.backend.service.impl.LoginService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @GetMapping("/create")
    public ModelAndView Create(ModelAndView modelAndView){
        modelAndView.setViewName("joker.html");
        return modelAndView;
    }
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Admin registerAdmin(@RequestBody AdminDTO admin){
        modelAndView.setViewName("register.html");
        return authenticationService.register(admin.getUsername(),admin.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse loginResponse(@RequestBody AdminDTO loginResponse){
        modelAndView.setViewName("login.html");
        return authenticationService.loginResponse(loginResponse.getUsername(),loginResponse.getPassword());
    }





}


