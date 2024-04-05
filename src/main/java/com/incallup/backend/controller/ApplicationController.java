package com.incallup.backend.controller;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.service.AdminQueryService;
import com.incallup.backend.service.ApplicationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationController {


    @Autowired
    private final AdminQueryService adminQueryService;
    @Autowired
    private final ApplicationQueryService applicationQueryService;

    @GetMapping("get/categories")
//    @Cacheable(value = "categories",key = "categories")
    public ModelAndView getCategoryList(ModelAndView modelAndView){
        modelAndView.setViewName("template-category");
        List<Category> listOfCategories = adminQueryService.listCategories();
        modelAndView.addObject("categories",listOfCategories);
        return modelAndView;
    }

    @GetMapping("get/categories/options")
    public ModelAndView getCategoryOptions(ModelAndView modelAndView){
        var categories = adminQueryService.listCategories();
        modelAndView.addObject("categories",categories);
        modelAndView.setViewName("category-options");
        return modelAndView;
    }

    @GetMapping("get/state/options")
    public ModelAndView getStatesOptions(ModelAndView modelAndView){
        List<String> states = adminQueryService.listStates();
        Collections.sort(states);
        modelAndView.addObject("states",states);
        modelAndView.setViewName("state-options");
        return modelAndView;
    }
    @GetMapping("/get/city/options")
    public ModelAndView getCitiesOptions(ModelAndView modelAndView,@RequestParam String state)
    {
        List<Location> locations = applicationQueryService.getLocationByState(state);
        locations.sort(new Location());
        modelAndView.addObject("locations",locations);
        modelAndView.setViewName("district-options");
        return modelAndView;
    }
}
