
package com.incallup.backend.controller;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.service.AdminQueryService;
import com.incallup.backend.service.ApplicationQueryService;
import com.incallup.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

@Autowired
private final ApplicationQueryService applicationQueryService;


    @GetMapping
    public ModelAndView Customer(ModelAndView model){
        model.setViewName("dashboard");
        List<Category> listOfCategories = adminQueryService.listCategories();

        model.addObject("categories", listOfCategories);
        System.out.println(model.getModel());
        return model;
    }




     @GetMapping("{category}/{location}/{titleString}")
    public ModelAndView Title(@PathVariable(name = "titleString") String title,ModelAndView modelAndView, @PathVariable(name = "category") String category, @PathVariable(name = "location") String location) throws ApplicationException {
//        Post post = customerService.searchByTitle(title);
         Post post = Post.builder()
                 .title(title)
                 .contact("123456789")
                 .description("post description")
                 .category(applicationQueryService.getCategoryByName(category))
                 .location(applicationQueryService.getLocationByName(location))
                 .build();
        modelAndView.addObject("post",post);
        modelAndView.setViewName("details");
        return modelAndView;

 
  
    }




    @GetMapping("{category}")
    public ModelAndView Category( @PathVariable(name = "category") String category, ModelAndView modelAndView) throws ApplicationException {

        if(category.equals("favicon.ico"))
            return null;


        var postsByCategory = customerService.searchByCategory(category);

        List<Post> posts = customerService.searchByCategory(category);
        if(posts.isEmpty()) {
            posts = new ArrayList<>();
            posts.add(Post.builder()
                    .age(26)
                    .title("sample post title")
                    .name("sample-post-title")
                    .views(420)
                    .contact("123456789")
                    .location(Location.builder().district("kolkata").state("West Bangal").build())
                    .description("did not put any description because this girl cannot be described in words and I don't get paid for enough also")
                    .build());
            posts.add(Post.builder()
                    .age(29)
                    .title("Lorem Ipsum Dummy TextLorem Ipsum Dummy Text Dummy Text  " +
                            "Text Lorem Ipsum Dummy TextLorem Ipsum Dummy ")
                    .title("lorem-ipsum")
                    .views(420)
                    .contact("123456789")
                    .location(Location.builder().district("kolkata").state("West Bangal").build())
                    .description("Lorem Ipsum Dummy TextLorem Ipsum Dummy Text Dummy Text   " +
                            "Text Lorem Ipsum Dummy TextLorem Ipsum Dummy ")
                    .build());
        }

//        posts.forEach(post -> {
//
//            post.setWhatsapp("https://api.whatsapp.com/send/?phone="+post.getContact());
//            post.setTelegram("https://t.me/"+post.getContact());
//            post.setCall("tel:"+post.getContact());
//
//        });

        modelAndView.addObject("posts",posts);
        modelAndView.addObject("category",applicationQueryService.getCategoryByName(category));
        modelAndView.setViewName("category");

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView Create(ModelAndView modelAndView){
        modelAndView.setViewName("create.html");
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @GetMapping("{category}/{location}")
    public ModelAndView Location(@PathVariable(name = "category") String category,@PathVariable(name = "location") String location, ModelAndView modelAndView) throws ApplicationException{
//        List<Post> posts = customerService.searchByCategoryAndLocation(category, location);

         List<Post> posts = new ArrayList<>();
        posts.add(Post.builder()
                .age(26)
                .title("sample post title")
                .name("sample-post-title")
                .views(420)
                .contact("123456789")
                .location(Location.builder().district("kolkata").state("West Bangal").build())
                .description("did not put any description because this girl cannot be described in words and I don't get paid for enough also")
                .build());
        posts.add(Post.builder()
                .age(29)
                .title("123456789012345")
                .name("sample-post-title")
                .views(420)
                .contact("123456789")
                .location(Location.builder().district("kolkata").state("West Bangal").build())
                .description("Lorem Ipsum Dummy TextLorem Ipsum Dummy Text Dummy Text   " +
                        "Text Lorem Ipsum Dummy TextLorem Ipsum Dummy ")
                .build());
        modelAndView.setViewName("category");
        modelAndView.addObject("posts",posts);
        modelAndView.addObject("category",applicationQueryService.getCategoryByName(category));
        modelAndView.addObject("location",applicationQueryService.getLocationByName(location));
        return modelAndView;
    }



}

