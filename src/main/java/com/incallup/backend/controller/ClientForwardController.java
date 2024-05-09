package com.incallup.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
public class ClientForwardController {


    @GetMapping("/react/{address}")
    public String forwardReact(@PathVariable String address){

        return "redirect:/server/assets/react/"+address;
    }
    @GetMapping("/static/{address}")
    public String forwardReactStatic(@PathVariable String address){

        return "redirect:/server/assets/react/static/react/static/"+address;
    }

    @GetMapping("/manifest.json")
    public String getManifest(){
        return "redirect:/server/assets/react/manifest.json";
    }

    @GetMapping("/static/css/main.f855e6bc.css")
    public String getCss(){
        return "redirect:/server/assets/react/static/css/main.f855e6bc.css";
    }
    @GetMapping("/static/js/main.0d4df0cd.js")
    public String getJs(){
        return "redirect:/server/assets/react/static/js/main.0d4df0cd.js";
    }

    @GetMapping("/static/media/logo.6ce24c58023cc2f8fd88fe9d219db6c6.svg")
    public String getLogo(){
        return "redirect:/server/assets/react/static/media/logo.6ce24c58023cc2f8fd88fe9d219db6c6.svg";
    }

}
