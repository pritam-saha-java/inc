package com.incallup.backend.controller;
import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.exception.ApplicationException;
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

    @GetMapping("/create")
    public ModelAndView getCreatePage(ModelAndView view){

        view.setViewName("admin-create");
        return view;
    }
    @GetMapping
    public ModelAndView Admin(ModelAndView model){
        model.setViewName("Welcome");
        return model;
    }

    @PostMapping("/category")
    public String createCategories(@ModelAttribute("title") String title,@ModelAttribute("meta") String meta,@ModelAttribute("description") String description) throws ApplicationException {

        var category = Category.builder().title(title).meta(meta).description(description).build();
        adminCommandService.createCategory(category);
        return "<script>alert('you have submitted category data successfully')</script>";
    }


    @PostMapping("/location")
    public String createLocations(@ModelAttribute("description") String description,@ModelAttribute("district") String district,@ModelAttribute("state") String state,@ModelAttribute("meta") String meta) throws ApplicationException {

        var location = Location.builder().description(description).district(district).state(state).meta(meta).build();

        adminCommandService.createLocation(location);

        return "<script>alert('you have submitted location data successfully')</script>";
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


