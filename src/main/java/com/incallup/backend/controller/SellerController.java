
package com.incallup.backend.controller;

import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.AccountCreationException;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;
import com.incallup.backend.exception.LogoutException;
import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import com.incallup.backend.service.impl.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("/get")
    public String getSession(Model model){
        model.addAttribute("seller","username");
        return "string";
    }

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


    @PostMapping("/auth/login")
    public ModelAndView Seller(ModelAndView model,@ModelAttribute("username") String username,@ModelAttribute("password") String password) throws LogoutException{
//        System.out.println("this is session attribute "+seller);
//        model.setViewName("dashboard");

        boolean authenticated = sellerQueryService.authenticate(username,password);
       if(authenticated)
       {
            model.addObject("seller",username);
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
        model.addObject("seller",seller);
        model.setViewName("profile");
        return model;
    }

    @PostMapping("/post/{sellerId}")
    public void Post(HttpSession session,@RequestBody Post post,@PathVariable(name = "sellerId") Integer sellerId) throws ApplicationException {
        authenticate(session);
        sellerCommandService.createPost(post,sellerId);
        log.info(post.toString());
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


        }

        model.setViewName("profile");
        return model;
    }





}

