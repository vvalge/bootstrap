package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Users;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users findById(Long id);

    List<Users> index();

    void saveUser(Users users);

    void deleteById(Long id);

    Users findByEmail(String email);
}
