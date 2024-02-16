
package com.incallup.backend.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/customer")

public class CustomerController {





    @GetMapping
    public String Customer(){
        return "/customer/welcome";
    }

    @GetMapping
    public String Title(){
        return "/title";
    }

    @GetMapping
    public String Category){
        return "/category";
    }


    @GetMapping
    public String Location(){
        return "/category/location";
    }

}

