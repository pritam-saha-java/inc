package com.incallup.backend.controller;
import com.incallup.backend.domain.Admin;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

@RestController
@RequestMapping("/admin")

//@GetMapping("/admin")
//@GetMapping("/admin/list/post")
//@GetMapping("/admin/list/sellers")
public class AdminController {


    @PostMapping("/admin")
    public void createAdmin(@RequestBody @Valid Admin admin){

    }

    @GetMapping
    public String Admin(){
        return "/admin/welcome";
    }


    @GetMapping("/create")
    public String Create(){
        return "Enter Data";
    }

    @GetMapping("/list/post")
    public String ListPost(){
        return "List All Admins";
    }

    @GetMapping("/list/sellers")
    public String ListSellers(){
        return "List of All Sellers";
    }

    @GetMapping("/block")
    public String Block(){
        return "To block an item";
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


