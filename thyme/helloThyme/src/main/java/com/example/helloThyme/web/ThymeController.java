package com.example.helloThyme.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeController {

    @RequestMapping("/hello")
    public String hello(
            @RequestParam String name,
            @RequestParam String age,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "hello";
    }
}
