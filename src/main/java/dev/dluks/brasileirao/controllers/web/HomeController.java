package dev.dluks.brasileirao.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/web")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to Brasileirão API");
        return "index";
    }
}

