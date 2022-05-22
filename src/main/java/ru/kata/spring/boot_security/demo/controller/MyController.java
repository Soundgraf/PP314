package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequiredArgsConstructor
public class MyController {
    private final UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        return "/admin";
    }
}