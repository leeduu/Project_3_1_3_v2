package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImpl userServiceImpl;
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")   // приветственная страница
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String findByUsername(Model model, Principal principal) {
        User user = (User) userServiceImpl.findByUsername(principal.getName());
        model.addAttribute("showUserDetails", user);
        return "user";
    }

//    вариант с Principal с кучей данных
//    @GetMapping("/user")
//    public String findByUsername(Model model, Principal principal, @AuthenticationPrincipal User user/*, @PathVariable("id") int id*/) {
//        model.addAttribute("showUserName", principal.toString());
//        return "user";
//    }
}