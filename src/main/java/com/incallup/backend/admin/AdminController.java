package com.incallup.backend.admin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

@RestController
@RequestMapping("/admin")

//@PostMapping("/admin")
//@GetMapping("/admin")
//@GetMapping("/admin/list/post")
//@GetMapping("/admin/list/sellers")
public class AdminController {



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


    @PutMapping("/block/seller/{id}")
    public String Profile(@PathVariable String adminId){
        return "Block a admin by id";
    }

    @PutMapping("/block/post/{id}")
    public String ProfilePost(@PathVariable String adminId){
        return "Let the admin post something by Id";
    }



}


