
package com.incallup.backend.controller;

import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;
import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
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


    @ExceptionHandler(ServletRequestBindingException.class)
    public String exception(HttpServletRequest request) {
        String referrer = request.getHeader("referer");
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.put("errorMessage","Execute A Query Then Retry");
//        modelAndView.setViewName("seller-session-error");
        return "please login first";
    }


    @Autowired
    private final SellerCommandService sellerCommandService;

    @Autowired
    private final SellerQueryService sellerQueryService;

    @GetMapping("/get")
    public String getSession(Model model){
        model.addAttribute("seller","hi");
        return "string";
    }

    @GetMapping("/auth/login")
    public String Seller(HttpSession session, ModelAndView model){
//        System.out.println("this is session attribute "+seller);
//        model.setViewName("dashboard");
        return "model";
    }

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


    @GetMapping("/post/{postId}")
    public ModelAndView PostId(HttpSession session,@PathVariable Integer postId, ModelAndView model) throws ApplicationException{
        var vatar = sellerQueryService.getPostById(postId);
        model.setViewName("details");
        return model;
    }

    @GetMapping("/post")
    public ModelAndView postView(HttpSession session, ModelAndView view){

         String seller = (String) session.getAttribute("seller");
            if(seller==null){

                view.setViewName("seller-session-error");
                return view;
            }
        view.setViewName("post");
        return view;
    }





    /**
     * not implemented
     * */
    @GetMapping("/{username}")
    public ModelAndView Username(@PathVariable String username, ModelAndView model){
        model.setViewName("profile");
        return model;
    }



}

