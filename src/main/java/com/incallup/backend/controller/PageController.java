package com.incallup.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class PageController {

    @GetMapping("/termAndCondition.html")
    public ModelAndView showTermAndCondition(ModelAndView model){

        model.setViewName("termAndCondition");

        return model;

    }

    @GetMapping("/FAQ.html")
    public ModelAndView showFAQ(ModelAndView model){

        model.setViewName("FAQ");

        return model;

    }


    @GetMapping("/privacy-policy.html")
    public ModelAndView showPrivacyPolicy(ModelAndView model){

        model.setViewName("privacy-policy");

        return model;

    }

    @GetMapping("/contact-us.html")
    public ModelAndView showContactUs(ModelAndView model){

        model.setViewName("contact-us");

        return model;

    }


    @GetMapping("/Blogs.html")
    public ModelAndView showBlogs(ModelAndView model){

        model.setViewName("Blogs");

        return model;

    }
    
}
