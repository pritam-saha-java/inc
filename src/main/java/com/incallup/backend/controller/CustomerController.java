
package com.incallup.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/customer")

public class CustomerController {





    @GetMapping
    public String Customer(){
        return "/customer/welcome";
    }



    @GetMapping("/title/{titleString}")
    public String Title(@PathVariable(required = true,name = "title") String title){
        return "/title";
    }

    @GetMapping("/{category}")
    public String Category(@PathVariable(required = true,name = "category") String category){
        return "/category";
    }



    @GetMapping("/{category}/{location}")
    public String Location(@PathVariable(required = true,name = "category") String category,@PathVariable(required = true,name = "location") String location){
        return "/category/location";
    }

}

