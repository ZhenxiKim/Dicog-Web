package com.example.diccogweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class RootController {

    @GetMapping("")
    public String getIndex(){
        return "/mm/index";
    }
}
