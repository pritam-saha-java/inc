
package com.incallup.backend.controller;

import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;
import com.incallup.backend.exception.LogoutException;
import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

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
        String referrer = request.getHeader("referer");
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.put("errorMessage","Execute A Query Then Retry");
//        view.setViewName("seller-session-error");
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

        return "string";
    }

    @PostMapping("/auth/login")
    public ModelAndView Seller(ModelAndView model,@ModelAttribute("username") String username,@ModelAttribute("password") String password){
//        System.out.println("this is session attribute "+seller);
//        model.setViewName("dashboard");j

        boolean authenticated = sellerQueryService.authenticate(username,password);
       if(authenticated)
       {
            model.addObject("seller",username);
            model.setViewName("profile");
       }

        return model;
    }
    //

    @GetMapping("/end")
    public String end(SessionStatus status){
        status.setComplete();
        return "end";
    }


    /**
     * delayed
     * */
    @PutMapping("/{sellerId}")
    public String SellerId(HttpSession session, @PathVariable String sellerId){
        System.out.println("Showing seller Id" + sellerId);
        return "Getting details of the seller";
    }




    @GetMapping("/list/{sellerId}")
    public ModelAndView ListId(HttpSession session,@PathVariable Integer sellerId, ModelAndView model){
        var mg = sellerQueryService.getPostsBySellerId(sellerId);
        model.setViewName("sellers");
        model.addObject("post", mg);
        return model;
    }

    @GetMapping("/profile/{sellerId}")
    public ModelAndView Profile(HttpSession session,@PathVariable Integer sellerId, ModelAndView model) throws IdNotFoundException {
        var na = sellerQueryService.getSellerById(sellerId);
        model.setViewName("profile");
        return model;
    }

    @PostMapping("/post/{sellerId}")
    public void Post(HttpSession session,@RequestBody Post post,@PathVariable(name = "sellerId") Integer sellerId) throws ApplicationException {
        sellerCommandService.createPost(post,sellerId);
        log.info(post.toString());
    }


    @GetMapping("get/post/{postId}")
    public ModelAndView PostId(HttpSession session,@PathVariable Integer postId, ModelAndView model) throws ApplicationException{
        var vatar = sellerQueryService.getPostById(postId);
        model.setViewName("details");
        return model;
    }

    @GetMapping("/post")
    public ModelAndView postView(HttpSession session, ModelAndView view)  throws LogoutException{

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

        modelAndView.setViewName("login");
        return modelAndView;
    }





    /**
     * not implemented
     * */
    @GetMapping("edit/{username}")
    public ModelAndView Username(@PathVariable String username, ModelAndView model){
        model.setViewName("profile");
        return model;
    }



}

