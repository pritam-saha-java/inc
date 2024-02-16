
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

    @GetMapping("/title")
    public String Title(){
        return "/title";
    }


    public String Category(){
        return "/category";
    }



    public String Location(){
        return "/category/location";
    }

}

