
package com.incallup.backend.controller;


import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/seller")
@Slf4j
@Service
@RequiredArgsConstructor
public class SellerController {

    private final SellerCommandService sellerCommandService;
    private final SellerQueryService sellerQueryService;

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

