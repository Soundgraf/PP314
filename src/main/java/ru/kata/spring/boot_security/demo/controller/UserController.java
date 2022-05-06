package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/listUser")
    public String listUser(Model model) {
        model.addAttribute("listUser", userService.getAllUsers());
        return "/user";
    }

    @GetMapping("/user")
    public String oneUser(Model model, Principal principal) {
        model.addAttribute("oneUser", userService.findByName(principal.getName()));
        return "user";
    }

    @GetMapping("/admin/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("oneUser", userService.getUser(id));
        return "user";
    }

    @GetMapping(value = "/admin")
    public String allUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "/admin/users";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(@ModelAttribute User user) {
        return "/admin/new";
    }

    @PostMapping(value = "/admin/new")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/admin/edit";
    }

    @PostMapping(value = "/admin/edit/{id}")
    public String updateUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}