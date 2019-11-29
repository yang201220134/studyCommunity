package com.ysy.studycommunity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/greet")
    public String greet(@RequestParam(name="name",defaultValue = "world",required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
    @PostMapping("/greet")
    public String greetPost(@RequestParam(name="name",defaultValue = "postwold",required = false) String name,Model model){
        model.addAttribute("name",name+"post");
        return "hello";
    }
}
