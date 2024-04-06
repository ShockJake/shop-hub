package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public record AuthController(UserService userService) {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String register() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(@RequestBody User user, Model model) {
        final User createdUser = userService.registerNewUser(user);
        model.addAttribute("userName", createdUser.getName());
        return "login";
    }
}
