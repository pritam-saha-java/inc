package com.incallup.backend.seller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

@RestController
@RequestMapping("/seller")
public class SellerController {

    //(https://www.incallup.com/seller/welcome)

    @GetMapping
    public String Seller(){
        return "/seller/welcome";
    }


    @PutExchange("/seller/{sellerId}")
    public String SellerId(@RequestBody SellerModel sellerId){
        System.out.println("Showing seller Id" + sellerId);
        return "Getting details of the seller";
    }

    /*
    @PutExchange("/seller/{sellerId}")
    public String SellerId(){
        return "Get details of a seller by Id";
    }
     */
    @GetMapping("/seller/list")
    public String List(){
        return "Show all sellers";
    }

    @GetMapping("/seller/list/{sellerId}")
    public String ListId(){
        return "Show all details of a seller";
    }

    @GetMapping("/seller/profile/{sellerId}")
    public String Profile(@PathVariable String sellerId){
        return "Show all details of a particular seller";
    }

    @PostMapping("/seller/post")
    public String Post(){
        return "Let the seller post things here";
    }


    @GetMapping("/seller/post/{postID}")
    public String PostId(){
        return "Post Id of a seller";
    }

    @GetMapping("/seller/{username}")
    public String Username(){
        return "Seller Username";
    }

    @GetMapping("/seller/login")
    public String Login(){
        return "Seller Login";
    }
}
