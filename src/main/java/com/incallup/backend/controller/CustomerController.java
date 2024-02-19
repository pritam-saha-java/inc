
package com.incallup.backend.controller;

import com.incallup.backend.domain.Category;
import com.incallup.backend.utility.IncallupConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
public class CustomerController {





    @GetMapping
    public ModelAndView Customer(ModelAndView model){
        model.setViewName("dashboard");

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(Category.builder()
                        .name("call-girl")
                        .title("Call girl")
                .build());
        categoryList.add(Category.builder()
                .name("adult-meetings")
                .title("Adult meetings")
                .build());
        categoryList.add(Category.builder()
                .name("massage")
                .title("Massage")
                .build());
        categoryList.add(Category.builder()
                .name("camera")
                .title("camera title")
                .build());


        model.addObject("categories", categoryList);

        return model;
    }



    @GetMapping("title/{titleString}")
    public String Title(@PathVariable(name = "titleString") String title){
        return "hello";
    }

    @GetMapping("{category}")
    public ModelAndView Category(@PathVariable(name = "category") String category,ModelAndView modelAndView){
        modelAndView.setViewName("category");

        return modelAndView;
    }



    @GetMapping("{category}/{location}")
    public String Location(@PathVariable(name = "category") String category,@PathVariable(name = "location") String location){
        return "/category/location";
    }

}

