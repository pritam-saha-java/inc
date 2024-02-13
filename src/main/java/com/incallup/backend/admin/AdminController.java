package com.incallup.backend.admin;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

@RestController
@RequestMapping("/admin")

//@PostMapping("/admin")
//@GetMapping("/admin")
//@GetMapping("/admin/list/post")
//@GetMapping("/admin/list/sellers")
//@RequestMapping("/")
public class AdminController {

    //(https://www.incallup.com/admin/welcome)

//    @ResponseBody

    @GetMapping
    public String Admin(){
        return "/admin/welcome";
    }

    public String Create(){
        return "Enter Data";
    }

    public String List(){
        return "List All Admins";
    }

    public String Block(){
        return "All Blocked Items";
    }


    @PutExchange("admin/block/seller/{id}")
    public String Profile(@PathVariable String adminId){
        return "Block a admin by id";
    }

    @PutExchange("admin/block/post/{id}")
    public String ProfilePost(@PathVariable String adminId){
        return "Let the admin post something by Id";
    }


}

