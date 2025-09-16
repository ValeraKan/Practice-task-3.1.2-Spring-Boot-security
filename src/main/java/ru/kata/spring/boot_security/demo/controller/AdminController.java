package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "admin/all-users";
    }

    @GetMapping("/admin/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/user-info";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(@RequestParam(name = "id", required = false) Long id,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email) {
        User user = new User();
        if (id != null) {
            user.setId(id);
        }
        user.setName(name);
        user.setEmail(email);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/updateUser")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "admin/user-info";
    }

    @PostMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
