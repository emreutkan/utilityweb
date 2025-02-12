package com.utility.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String landing() {
        // This returns the "landing.html" template from src/main/resources/templates
        return "landing";
    }
}
