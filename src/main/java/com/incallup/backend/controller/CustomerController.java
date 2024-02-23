
package com.incallup.backend.controller;

import com.incallup.backend.domain.Category;
import com.incallup.backend.service.CustomerService;
import com.incallup.backend.utility.IncallupConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/")
@Service
@RequiredArgsConstructor
public class CustomerController {

@Autowired
private final CustomerService customerService;


    @GetMapping
    public ModelAndView Customer(ModelAndView model){
        model.setViewName("dashboard");
        final Set<Category> categoryList = new LinkedHashSet<>();
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


        model.addObject("categories", categoryList.stream().toList());

        return model;
    }



    @GetMapping("title/{titleString}")
    public String Title(@PathVariable(name = "titleString") String title){
        return "joker";
    }


    @GetMapping("{category}")
    public ModelAndView Category(@PathVariable(name = "category") String category,ModelAndView modelAndView){
        modelAndView.setViewName("category");
        modelAndView.addObject("categoryName",category);
        return modelAndView;
    }



    @GetMapping("{category}/{location}")
    public String Location(@PathVariable(name = "category") String category,@PathVariable(name = "location") String location){
        return "/category/location";
    }


    @GetMapping("get/categories")
    public ModelAndView getCategoryList(ModelAndView modelAndView){
        final Set<Category> categoryList = new LinkedHashSet<>();
        modelAndView.setViewName("template");

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
        modelAndView.addObject("category",categoryList.stream().toList());
        return modelAndView;
    }

}

