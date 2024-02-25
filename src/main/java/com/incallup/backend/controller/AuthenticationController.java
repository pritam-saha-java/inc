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
public class AuthenticationController {

    @GetMapping("/create")
    public ModelAndView Create(ModelAndView modelAndView){
        modelAndView.setViewName("create.html");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Admin registerAdmin(@RequestBody AdminDTO admin){

        return authenticationService.register(admin.getUsername(),admin.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse loginResponse(@RequestBody AdminDTO loginResponse){

        return authenticationService.loginResponse(loginResponse.getUsername(),loginResponse.getPassword());
    }





}


