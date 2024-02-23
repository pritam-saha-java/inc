
package com.incallup.backend.controller;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Post;
import com.incallup.backend.utility.IncallupConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;

@RestController
@Slf4j
@RequestMapping("/")
public class CustomerController {





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



    @GetMapping("{category}/{location}/{titleString}")
    public String Title(@PathVariable(name = "titleString") String title, @PathVariable String category, @PathVariable String location){
        return category+"/"+location+"/"+title;
    }

    @GetMapping("{category}")
    public ModelAndView Category(@PathVariable(name = "category") String category,ModelAndView modelAndView){
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

