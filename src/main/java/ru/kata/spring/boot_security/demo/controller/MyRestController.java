package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyRestController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/api/user")
    public User getUserByUsername (Principal principal) {
        User user = userRepository.findUserByEmail(principal.getName());
        return user;
    }

    @GetMapping("/api/admin")
    public List<User> userList(){
        return userService.listUsers();
    }

    @GetMapping("/api/admin/{id}")
    public User showUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/api/admin")
    public List<User> addUser(@RequestBody User user){
        userService.add(user, user.getRoles());
        return userService.listUsers();
    }

    @PutMapping("/api/admin")
    public User update(@RequestBody User user){
        userService.change(user, user.getRoles());
        return user;
    }

    @DeleteMapping("/api/admin/{id}")
    public List<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return userService.listUsers();
    }
}