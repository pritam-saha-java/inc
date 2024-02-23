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

@RestController
@RequestMapping("/admin")
@Slf4j
@Service
@RequiredArgsConstructor
//@GetMapping("/admin")
//@GetMapping("/admin/list/post")
//@GetMapping("/admin/list/sellers")
public class AdminController {

    @Autowired
    private final AdminCommandService adminCommandService;
    private final AdminQueryService adminQueryService;

    @PostMapping("/admin")
    public void createAdmin(@RequestBody @Valid Admin admin){

    }

    @GetMapping
    public String Admin(){
        return "/admin/welcome";
    }

    @GetMapping("/list/post")
    public String ListPost(){
        return "List All Admins";
    }

    @GetMapping("/list/sellers")
    public String ListSellers(){
        return "List of All Sellers";
    }

    @GetMapping("/block")
    public String Block(){
        return "To block an item";
    }


    @PutMapping("/block/seller/{id}")
    public String Profile(@PathVariable Integer adminId){
        return "Block a admin by id";
    }

    @PutMapping("/block/post/{id}")
    public String ProfilePost(@PathVariable Integer adminId){
        return "Let the admin post something by Id";
    }



}


