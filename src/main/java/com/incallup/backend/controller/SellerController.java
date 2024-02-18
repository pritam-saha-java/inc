
package com.incallup.backend.controller;


import com.incallup.backend.domain.Seller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {



    @GetMapping
    public String Seller(){
        return "/seller/welcome";
    }


    @PutExchange("/{sellerId}")
    public String SellerId(@RequestBody Seller sellerId){
        System.out.println("Showing seller Id" + sellerId);
        return "Getting details of the seller";
    }


    @GetMapping("/list")
    public String List(){
        return "Show all sellers";
    }

    @GetMapping("/list/{sellerId}")
    public String ListId(){
        return "Show all details of a seller";
    }

    @GetMapping("/profile/{sellerId}")
    public String Profile(@PathVariable String sellerId){
        return "Show all details of a particular seller";
    }

    @PostMapping("/post")
    public String Post(){
        return "Let the seller post things here";
    }


    @GetMapping("/post/{postID}")
    public String PostId(){
        return "Post Id of a seller";
    }

    @GetMapping("/{username}")
    public String Username(){
        return "Seller Username";
    }


    @GetMapping("/seller/login")
    public String Login(){
        return "Seller Login";
    }
}

