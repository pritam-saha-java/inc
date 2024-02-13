package com.incallup.backend.seller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {

    @GetMapping
    public String hello(){
                log.info("hello from logs");
        return "hello";}

}
