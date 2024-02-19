
package com.incallup.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CustomerController {





    @GetMapping
    public String Customer(Model model){
        model.addAttribute("open","USA");
        return "joker";
    }



    @GetMapping("/title/{titleString}")
    public String Title(@PathVariable(name = "title") String title){
        return "/title";
    }

    @GetMapping("/{category}")
    public String Category(@PathVariable(name = "category") String category){
        return "/category";
    }



    @GetMapping("/{category}/{location}")
    public String Location(@PathVariable(name = "category") String category,@PathVariable(name = "location") String location){
        return "/category/location";
    }

}

