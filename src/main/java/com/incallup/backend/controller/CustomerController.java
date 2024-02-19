
package com.incallup.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
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
    public ModelAndView Customer(){
        ModelAndView model = new ModelAndView("joker");
        model.addObject("open","USA");
        return model;
    }



    @GetMapping("title/{titleString}")
    public String Title(@PathVariable(name = "title") String title){
        return "/title";
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

