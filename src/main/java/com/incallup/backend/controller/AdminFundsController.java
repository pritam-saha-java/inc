package com.incallup.backend.controller;

import com.incallup.backend.service.AdminFundsService;
import org.springframework.stereotype.Controller;

@Controller
public class AdminFundsController {

    private final AdminFundsService service;

    public AdminFundsController(AdminFundsService service) {
        this.service = service;
    }
}
