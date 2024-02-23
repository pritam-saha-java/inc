
package com.incallup.backend.controller;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.repository.CategoryRepository;
import com.incallup.backend.service.AdminQueryService;
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

@Autowired
private final AdminQueryService adminQueryService;


    @GetMapping("get/categories")
    public ModelAndView Customer(ModelAndView model){
        model.setViewName("dashboard");
        List<Category> listOfCategories = adminQueryService.listCategories();
        model.addObject("category", listOfCategories);
        return model;
    }



    @GetMapping("title/{titleString}")
    public ModelAndView Title(@PathVariable(name = "titleString") String title,ModelAndView modelAndView) throws ApplicationException {
        Post post = customerService.searchByTitle(title);
        modelAndView.addObject("post",post);
        modelAndView.setViewName("post");
        return modelAndView;
    }


    @GetMapping("{category}")
    public ModelAndView Category(@PathVariable(name = "category") String category,ModelAndView modelAndView){
        List<Category> ns = adminQueryService.listCategories();
        modelAndView.setViewName("category");
        modelAndView.addObject("categoryName",category);
        return modelAndView;
    }



    @GetMapping("{category}/{location}")
    public ModelAndView Location(@PathVariable(name = "category") String category,@PathVariable(name = "location") String location, ModelAndView model) throws ApplicationException{
        List<Post> cateGory = customerService.searchByCategoryAndLocation(category, location);
        model.setViewName("category");
        return model;
    }


    @GetMapping("get/categories")
    public ModelAndView getCategoryList(ModelAndView modelAndView){
        modelAndView.setViewName("template");
        List<Category> listOfCategories = adminQueryService.listCategories();
        modelAndView.addObject("category",listOfCategories);
        return modelAndView;
    }


}

