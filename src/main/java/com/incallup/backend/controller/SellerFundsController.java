package com.incallup.backend.controller;

import com.incallup.backend.domain.TopUpPackageEntity;
import com.incallup.backend.service.SellerFundsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RequestMapping("/packages")
@Controller
@Log4j2
public class SellerFundsController {
    private final SellerFundsService service;

    public SellerFundsController(SellerFundsService service) {
        this.service = service;
    }

    @GetMapping("/get-all-packages")
    public ModelAndView getCategoryOptions(ModelAndView modelAndView){
        List<TopUpPackageEntity> allTopUpPackages = service.getAllTopUpPackages();
        modelAndView.addObject("packages",allTopUpPackages);
        modelAndView.setViewName("packages-page");
        return modelAndView;
    }

    @GetMapping("/purchase-package")
    public ModelAndView purchasePackage(ModelAndView modelAndView,
                                        @ModelAttribute("packageId") String packageId,
                                        @ModelAttribute("username") String username){

        return purchasePackage(modelAndView, packageId, username);
    }

    @PostMapping("/packages/purchase-package")
    public String purchasePackage(@RequestParam("packageId") Long packageId, RedirectAttributes redirectAttributes) {
        // Handle the package purchase logic here
        // ...

        // Redirect to the next page after processing
        return "redirect:/next-page";
    }

}
