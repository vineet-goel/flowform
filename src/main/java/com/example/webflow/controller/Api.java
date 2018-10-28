package com.example.webflow.controller;

import com.example.webflow.vm.UserVM;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Api {


    @RequestMapping(value = "/flow", method = RequestMethod.GET)
    public String body(Model model) {

        model.addAttribute("user", UserVM.builder().age(5).name("Vin").build());
        return "main";
    }
}
