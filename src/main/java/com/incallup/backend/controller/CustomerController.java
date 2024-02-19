
package com.incallup.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@RequestMapping("/")
public class CustomerController {





    @GetMapping
    public ModelAndView Customer(ModelAndView model){
        model.setViewName("joker");
        model.addObject("open","USA");
        return model;
    }



    @GetMapping("title/{titleString}")
    public String Title(@PathVariable(name = "titleString") String title){
        return "hello";
    }

    @GetMapping("{category}")
    public String Category(@PathVariable(name = "category") String category){
        return "/category";
    }



    @GetMapping("{category}/{location}")
    public String Location(@PathVariable(name = "category") String category,@PathVariable(name = "location") String location){
        return "/category/location";
    }

}

