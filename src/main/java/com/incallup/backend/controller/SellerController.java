
package com.incallup.backend.controller;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.AccountCreationException;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;
import com.incallup.backend.exception.LogoutException;
import com.incallup.backend.service.AdminQueryService;
import com.incallup.backend.service.CustomerService;
import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import com.incallup.backend.service.impl.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequestMapping("/seller")
@Slf4j
@Service
@RequiredArgsConstructor
@SessionAttributes("seller")
public class SellerController {




    @ExceptionHandler(LogoutException.class)
    public ModelAndView exception(HttpServletRequest request,LogoutException exception) {
        ModelAndView view = new ModelAndView();
//        String referrer = request.getHeader("referer");
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.put("errorMessage","Execute A Query Then Retry");
        view.addObject("login",exception.getDescription());
        view.setViewName("login");
        return view;
    }


    @Autowired
    private final SellerCommandService sellerCommandService;

    @Autowired
    private final SellerQueryService sellerQueryService;

//    @GetMapping("/get")
//    public String getSession(Model model){
//        model.addAttribute("seller","username");
//        return "string";
//    }

    @Autowired
    private AuthenticationService authenticationService;

    @ExceptionHandler(AccountCreationException.class)
    public ResponseEntity accountAlreadyExists(AccountCreationException exception){

        return ResponseEntity.ofNullable(AccountCreationException.builder()
                        .title(exception.getTitle())
                        .Description(exception.getDescription())
                        .status(exception.getStatus())
                .build());
    }

    @PostMapping("/create")
    public String getSign(@ModelAttribute(name = "password")String password,@ModelAttribute("username") String username,@ModelAttribute("email") String email) throws AccountCreationException {


        sellerCommandService.register(
                Seller.builder()
                        .username(username)
                        .email(email)
                        .password(password)
                        .build()
        );
        return "sign"+password+email+username;
    }


    @PostMapping("/login")
    public ModelAndView Seller(ModelAndView model,@ModelAttribute("username") String username,@ModelAttribute("password") String password) throws LogoutException{
//        System.out.println("this is session attribute "+seller);
//        model.setViewName("dashboard");

        boolean authenticated = sellerQueryService.authenticate(username,password);
       if(authenticated)
       {
           var seller = sellerQueryService.getSellerByUsername(username);
            model.addObject("seller",seller.getUsername());
            model.addObject("seller1",seller);
            model.setViewName("profile");
       }

        return model;


    }

    @GetMapping("/end")
    public View end(SessionStatus status){
        status.setComplete();
        return new RedirectView("login");
    }


    /**
     * delayed
     * */
    @PutMapping("/{sellerId}")
    public String SellerId(HttpSession session, @PathVariable String sellerId){
        authenticate(session);
        System.out.println("Showing seller Id" + sellerId);
        return "Getting details of the seller";
    }


    @Autowired
    private final AdminQueryService adminQueryService;
    @Autowired
    private final CustomerService customerService;

    @GetMapping("/list/{sellerId}")
    public ModelAndView ListId(HttpSession session,@PathVariable Integer sellerId, ModelAndView model) throws IdNotFoundException{
        authenticate(session);
        var mg = sellerQueryService.getPostsBySellerId(sellerId);
        model.setViewName("sellers");
        model.addObject("post", mg);
        return model;
    }

    @GetMapping("/profile/{sellerId}")
    public ModelAndView Profile(HttpSession session,@PathVariable Integer sellerId, ModelAndView model) throws IdNotFoundException {
        authenticate(session);
        var seller = sellerQueryService.getSellerById(sellerId);
        model.addObject("seller1",seller);
        model.setViewName("profile");
        return model;
    }

    @PostMapping(value = "/post/{sellerId}",headers = "Content-Type=multipart/form-data")
    public ModelAndView Post(HttpSession session
                ,@RequestParam("image1") MultipartFile multipartFile1
                ,@RequestParam("image2") MultipartFile multipartFile2
                ,@ModelAttribute("category") String category
                ,@ModelAttribute("title") String title
                ,@ModelAttribute("description") String description
                ,@ModelAttribute("contact") String contact
                ,@ModelAttribute("whatsapp") String whatsapp
                ,@ModelAttribute("telegram") String telegram
                ,@ModelAttribute("age") Integer age
                ,@ModelAttribute("state") String state
                ,@ModelAttribute("incall") String incall
                ,@ModelAttribute("outcall") String outcall
                ,@ModelAttribute("city") String city
                ,@PathVariable(name = "sellerId") Integer sellerId) throws ApplicationException, IOException {
        authenticate(session);
        log.info(multipartFile1.getOriginalFilename());
        log.info(multipartFile2.getOriginalFilename());
        Post post = Post.builder()
                .age(age)
                .title(title)
                .description(description)
                .contact(contact)
                .isBlocked(false)
                .category(Category.builder()
                        .name(category)
                        .build())
                .incall(incall)
                .outcall(outcall)
                .whatsapp(whatsapp.equals("on"))
                .telegram(telegram.equals("on"))
                .location(Location.builder()
                        .district(city.trim().replace(" ","-"))
                        .state(state)
                        .build())
                .build();
        log.info(post.toString());

        sellerCommandService.createPost(post,sellerId,multipartFile1,multipartFile2);
        log.info(post.toString());

        var model = new ModelAndView("profile");
        var seller = sellerQueryService.getSellerById(sellerId);
        model.addObject("seller1",seller);
        return model;
    }


    @GetMapping("/get/post/{postId}")
    public ModelAndView PostId(HttpSession session,@PathVariable Integer postId, ModelAndView model) throws IdNotFoundException{
        authenticate(session);
        var post = sellerQueryService.getPostById(postId);
        model.addObject("post",post);
        model.setViewName("details");
        return model;
    }

    @GetMapping("/post")
    public ModelAndView postView(HttpSession session, ModelAndView view)  throws LogoutException{
        log.info("inside post method");
        authenticate(session);
        var username = (String) session.getAttribute("seller");
        var seller = sellerQueryService.getSellerByUsername(username);
        var sellerId = seller.getId();
        view.addObject("sellerId",sellerId);


        view.setViewName("post");
        return view;
    }

    private static void authenticate(HttpSession session) throws LogoutException {
        String seller = (String) session.getAttribute("seller");
        log.info("seller object");
        log.info(seller);
        if(seller==null){
            throw new LogoutException();
        }
    }

    @GetMapping({"/login","/",""})
    public ModelAndView login(ModelAndView modelAndView){
            log.info("inside login method");
        modelAndView.setViewName("login");
        return modelAndView;
    }





    /**
     * not implemented
     * */
    @GetMapping({"edit/{username}","/profile"})
    public ModelAndView Username(HttpSession session, @PathVariable(required = false) String username, ModelAndView model) throws LogoutException{
        authenticate(session);
        if(username!=null){
            log.info(username);
            model.addObject("seller1",sellerQueryService.getSellerByUsername(username));

        }else {
        var postUser = (String) session.getAttribute("seller");
            model.addObject("seller1",sellerQueryService.getSellerByUsername(postUser));


        }

        model.setViewName("profile");
        return model;
    }





}

