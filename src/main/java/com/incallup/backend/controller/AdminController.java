package com.incallup.backend.controller;

//import com.incallup.backend.configuration.WHMService;

import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.LogoutException;
import com.incallup.backend.service.AdminCommandService;
import com.incallup.backend.service.AdminQueryService;
import com.incallup.backend.service.impl.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping
    public void createAdmin(@RequestBody @Valid Admin admin){

    }

    @Autowired
    public AuthenticationService authenticationService;
    @PostMapping("/login")
    public ModelAndView Seller(ModelAndView model,@ModelAttribute("username") String username,@ModelAttribute("password") String password) throws LogoutException {

        var loginResponse = authenticationService.adminLoginResponse(username,password);
        model.addObject("username",loginResponse.getUsername());
        model.setViewName("admin-create");
        return model;

    }
    @GetMapping("/create")
    public ModelAndView getCreatePage(ModelAndView view){

        view.setViewName("admin-create");
        return view;
    }
    @GetMapping
    public ModelAndView Admin(ModelAndView model){
        model.setViewName("admin");
        return model;
    }

//    public String convertInputStreamToByteArray(InputStream inputStream) throws IOException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] buffer = new byte[4096];
//        int bytesRead;
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, bytesRead);
//        }
//        return Base64.getEncoder().encodeToString((outputStream.toByteArray()));
//
////        return outputStream.toByteArray();
//    }

    @PostMapping(value = "/category",headers = "Content-Type=multipart/form-data")
    public String createCategories(@ModelAttribute("title") String title, @ModelAttribute("meta") String meta, @ModelAttribute("description") String description

                                   ,@RequestParam("picture") MultipartFile multipartFile

    ) throws ApplicationException{


        var category = Category.builder().title(title).meta(meta).description(description).build();
////        byte[] bytes ;
//        try {
////            category.setImageData(convertInputStreamToByteArray(multipartFile.getInputStream()));
//        }catch (Exception e){
//            log.error("file not found");
//        }


        adminCommandService.createCategory(category,multipartFile);
        return "<script>alert('you have submitted category data successfully')</script>";
    }



    @PostMapping("/location")
    public String createLocations(@ModelAttribute("description") String description,@ModelAttribute("city") String district,@ModelAttribute("state") String state,@ModelAttribute("meta") String meta) throws ApplicationException {

        var location = Location.builder().description(description).district(district).state(state).meta(meta).build();

        adminCommandService.createLocation(location);

        return "<script>alert('you have submitted location data successfully')</script>";
    }

    @GetMapping("/list/post")
    public ModelAndView ListPost(ModelAndView model){
        model.setViewName("category");
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
    public void Profile(@PathVariable Integer sellerId){

        adminCommandService.blockSeller(sellerId);

    }

    @PutMapping("/block/post/{postId}")
    public void ProfilePost(@PathVariable Integer postId){

        adminCommandService.blockPost(postId);

    }


}


