package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public record AccountController(UserService userService) {

}
