
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


import java.util.*;

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


    @GetMapping
    public ModelAndView Customer(ModelAndView model){
        model.setViewName("dashboard");
        List<Category> listOfCategories = adminQueryService.listCategories();

        model.addObject("categories", listOfCategories);
        System.out.println(model.getModel());
        return model;
    }




     @GetMapping("{category}/{location}/{titleString}")
    public ModelAndView Title(@PathVariable(name = "titleString") String title,ModelAndView modelAndView, @PathVariable String category, @PathVariable String location) throws ApplicationException {
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
        List<String> categories = List.of("one","two","three");
        if(!categories.contains(category))
            throw new RuntimeException();

        List<Post> posts = new ArrayList<>();
        posts.add(Post.builder()
                        .age(16)
                        .title("sample post title")
                        .views(420)
                        .contact("1001")
                        .description("did not put any description because this girl cannot be described in words and I don't get paid for enough also")
                .build());
        posts.add(Post.builder()
                .age(69)
                .title("Lorem Ipsum Dummy TextLorem Ipsum Dummy Text Dummy Text  " +
                        "Text Lorem Ipsum Dummy TextLorem Ipsum Dummy ")
                .views(420)
                .contact("3456789123")
                .description("Lorem Ipsum Dummy TextLorem Ipsum Dummy Text Dummy Text   " +
                        "Text Lorem Ipsum Dummy TextLorem Ipsum Dummy ")
                .build());

        modelAndView.addObject("posts",posts);

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
        modelAndView.setViewName("template-category");
        List<Category> listOfCategories = adminQueryService.listCategories();
        modelAndView.addObject("category",listOfCategories);
        return modelAndView;
    }


}

