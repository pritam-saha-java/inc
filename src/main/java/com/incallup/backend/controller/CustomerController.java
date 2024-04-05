
package com.incallup.backend.controller;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.service.AdminQueryService;
import com.incallup.backend.service.ApplicationQueryService;
import com.incallup.backend.service.CustomerService;
import com.incallup.backend.service.impl.SiteMapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
@Service
@RequiredArgsConstructor
public class CustomerController {


    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getRobotsTxt() {
        return """
                User-agent: AhrefsBot
                Disallow: /
                                
                User-agent: YandexBot
                Disallow: /
                                
                User-agent: BLEXBot
                Disallow: /
                                
                User-agent: Qwantify
                Disallow: /
                                
                User-agent: ia_archiver
                Disallow: /
                
                User-agent: *
                Allow: /
                Allow: *p=1$
                Disallow: *p=*
                Disallow: /u/
                Disallow: /fe/
                Disallow: *tags=*
                Disallow: */related/
                Sitemap: https://www.incallup.com/sitemap.xml
                """;
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public String getSiteMap() {



        return siteMapService.siteMap();

    }

//    @GetMapping(value = "sitemap/{pageName}", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    @GetMapping(value = "sitemap/{pageName}", produces = MediaType.APPLICATION_XML_VALUE)
    public String getSiteMap(@PathVariable(name = "pageName") String pageName) {
        return switch (pageName){
            case "category.xml"->siteMapService.categorySiteMap();
//            case "location.xml"->siteMapService.locationSiteMap("west bengal");
            case "titles.xml"->siteMapService.postSiteMap();
            default ->" sitemap error ";
        };

    }

    @GetMapping(value = "sitemap/location/{state}",produces = MediaType.APPLICATION_XML_VALUE)
//    @GetMapping(value = "sitemap/location/{state}",produces = MediaType.TEXT_PLAIN_VALUE)
    public String getLocationSiteMap(@PathVariable(name = "state") String state){

        return siteMapService.locationSiteMap(state);
    }

    @Autowired
    private final SiteMapService siteMapService;

@Autowired
private final CustomerService customerService;

@Autowired
private final AdminQueryService adminQueryService;

@Autowired
private final ApplicationQueryService applicationQueryService;

private final ApplicationController applicationController;

    @GetMapping
    public ModelAndView Customer(ModelAndView model){
        model.setViewName("dashboard");
        List<Category> listOfCategories = adminQueryService.listCategories();
        model.addObject("categories", listOfCategories);
        model.addObject("description", applicationQueryService.getSiteDescription());
        System.out.println(model.getModel());
        return model;
    }




     @GetMapping("{category}/{location}/{titleString}")
    public ModelAndView Title(@PathVariable(name = "titleString") String title,ModelAndView modelAndView, @PathVariable(name = "category") String category, @PathVariable(name = "location") String location) throws ApplicationException {
        Post post = customerService.searchByTitle(title);
        modelAndView.addObject("description", applicationQueryService.getSiteDescription());
        modelAndView.addObject("post",post);
        modelAndView.setViewName("details");
        return modelAndView;

 
  
    }




    @GetMapping("{category}")
    public ModelAndView Category( @PathVariable(name = "category") String category, ModelAndView modelAndView) throws ApplicationException {

        if(category.equals("favicon.ico"))
            return null;


        var customPages = List.of("blogs", "contact","terms","privacy","faq");
        if (customPages.contains(category)){
            modelAndView.setViewName("site-"+category);
            return modelAndView;
        }



        var postsByCategory = customerService.searchByCategory(category);

        List<Post> posts = customerService.searchByCategory(category);


        modelAndView.addObject("description", applicationQueryService.getSiteDescription());
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
        List<Post> posts = customerService.searchByCategoryAndLocation(category, location);


        modelAndView.setViewName("category");
        modelAndView.addObject("posts",posts);
        modelAndView.addObject("category",applicationQueryService.getCategoryByName(category));
        modelAndView.addObject("location",applicationQueryService.getLocationByName(location));
        return modelAndView;
    }



}

