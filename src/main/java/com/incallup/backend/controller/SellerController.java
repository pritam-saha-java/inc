
package com.incallup.backend.controller;


import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;
import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/seller")
@Slf4j
@Service
@RequiredArgsConstructor
public class SellerController {

    @Autowired
    private final SellerCommandService sellerCommandService;

    @Autowired
    private final SellerQueryService sellerQueryService;

    @GetMapping
    public ModelAndView Seller(ModelAndView model){
        model.setViewName("posts");
        return model;
    }


    /**
     * delayed
     * */
    @PutMapping("/{sellerId}")
    public String SellerId( @PathVariable String sellerId){
        System.out.println("Showing seller Id" + sellerId);
        return "Getting details of the seller";
    }




    @GetMapping("/list/{sellerId}")
    public ModelAndView ListId(@PathVariable Integer sellerId, ModelAndView model){
        var mg = sellerQueryService.getPostsBySellerId(sellerId);
        model.setViewName("sellers");
        model.addObject("post", mg);
        return model;
    }

    @GetMapping("/profile/{sellerId}")
    public ModelAndView Profile(@PathVariable Integer sellerId, ModelAndView model) throws IdNotFoundException {
        var na = sellerQueryService.getSellerById(sellerId);
        model.setViewName("profile");
        return model;
    }

    @PostMapping("/post/{sellerId}")
    public void Post(@RequestBody Post post,@PathVariable(name = "sellerId") Integer sellerId) throws ApplicationException {
        sellerCommandService.createPost(post,sellerId);
        log.info(post.toString());
    }


    @GetMapping("/post/{postId}")
    public ModelAndView PostId(@PathVariable Integer postId, ModelAndView model) throws ApplicationException{
        var vatar = sellerQueryService.getPostById(postId);
        model.setViewName("posts");
        return model;
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

