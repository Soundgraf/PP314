package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    public Role getRoleByName(String role) {
        return roleRepository.findByName(role);
    }

    public void createRole(Role role) {
        roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.delete(roleRepository.getById(id));
    }





    public void add(User user,String[] roles) {
        user.setRoles(Arrays.stream(roles)
                .map(roleRepository::findByName)
                .collect(Collectors.toSet()));
    }

    public void change(User user,Set<Role> roles) {
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }



//    public User getUser(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//    public void save(User user, String[] roles) {
//        user.setRoles(Arrays.stream(roles)
//                .map(roleRepository::findByName)
//                .collect(Collectors.toList()));
//        userRepository.save(user);
//    }
//    public User findByName(String name) {
//        return userRepository.findByEmail(name);
//    }
}