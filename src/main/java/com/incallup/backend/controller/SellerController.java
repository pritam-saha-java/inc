
package com.incallup.backend.controller;


import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {



    @GetMapping
    public String Seller(){
        return "posts";
    }


    @PutMapping("/{sellerId}")
    public String SellerId( @PathVariable String sellerId){
        System.out.println("Showing seller Id" + sellerId);
        return "Getting details of the seller";
    }


    @GetMapping("/list")
    public String List(){
        return "Show all sellers";
    }

    @GetMapping("/list/{sellerId}")
    public String ListId(@PathVariable String sellerId){
        return "Show all details of a seller";
    }

    @GetMapping("/profile/{sellerId}")
    public String Profile(@PathVariable String sellerId){
        return "Show all details of a particular seller";
    }

    @PostMapping("/post")
    public void Post(@RequestBody Post post){
        log.info(post.toString());
    }


    @GetMapping("/post/{postId}")
    public String PostId(@PathVariable Integer postId){
        return "post";
    }

    @GetMapping("/{username}")
    public String Username(@PathVariable String username){
        return "profile";
    }



}

