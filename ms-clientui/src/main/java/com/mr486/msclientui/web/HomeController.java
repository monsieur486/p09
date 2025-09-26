package com.mr486.msclientui.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping({"/", "/home"})
  public String home(Model model) {
    model.addAttribute("message", "Bienvenue sur la page publique !");
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
