package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.Request;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.RequestService;
import com.university.shophub.backend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/management")
public record ManagementController(UserService userService, RequestService requestService, CategoryService categoryService) {

    @GetMapping
    public String managementPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "management";
    }

    @GetMapping("/users")
    public String manageUsersPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "management/users";
    }

    @GetMapping("/products")
    public String managePostsPage(Model model) {
        return "management/products";
    }

    @GetMapping("/requests")
    public String manageRequestsPage(Model model) {
        List<Request> requests = requestService.getAllRequests();
        model.addAttribute("requests", requests);
        return "management/requests";
    }

    @GetMapping("/categories")
    public String manageCategoriesPage(Model model) {
        return "management/categories";
    }

}
