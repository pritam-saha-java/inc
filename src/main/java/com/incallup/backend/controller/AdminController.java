package com.incallup.backend.controller;
import com.incallup.backend.domain.Admin;
import com.incallup.backend.service.AdminCommandService;
import com.incallup.backend.service.AdminQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
@Slf4j
@Service
@RequiredArgsConstructor
//@GetMapping("/admin")
//@GetMapping("/admin/list/post")
//@GetMapping("/admin/list/sellers")
//create post method for category
public class AdminController {

    @Autowired
    private final AdminCommandService adminCommandService;
    private final AdminQueryService adminQueryService;

    @PostMapping("/admin")
    public void createAdmin(@RequestBody @Valid Admin admin){

    }

    @GetMapping
    public ModelAndView Admin(ModelAndView model){
        model.setViewName("Welcome");
        return model;
    }

    @GetMapping("/categories")
    public ModelAndView getCategories(ModelAndView model){
        model.setViewName("Welcome");
        var categories = adminQueryService.listCategories();
        model.addObject("categories",categories);
        return model;
    }

    @GetMapping("/list/post")
    public ModelAndView ListPost(ModelAndView model){
        model.setViewName("post");
        var posts = adminQueryService.listPosts();
        model.addObject("posts",posts);
        return model;
    }

    @GetMapping("/list/sellers")
    public ModelAndView ListSellers(ModelAndView model){
        model.setViewName("seller");
        var sellers = adminQueryService.listSellers();
        model.addObject("sellers",sellers);
        return model;
    }

//    @GetMapping("/block")
//    public ModelAndView Block(ModelAndView model){
//        model.setViewName("block");
//        return model;
//    }


    @PutMapping("/block/seller/{sellerId}")
    public ModelAndView Profile(@PathVariable Integer sellerId, ModelAndView model){
        model.setViewName("sellerId");
        adminCommandService.blockPost(sellerId);
        return model;
    }

    @PutMapping("/block/post/{postId}")
    public ModelAndView ProfilePost(@PathVariable Integer postId, ModelAndView model){
        model.setViewName("postId");
        adminCommandService.blockPost(postId);
        return model;
    }

}


