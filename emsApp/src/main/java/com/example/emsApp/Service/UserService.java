package com.example.emsApp.Service;

import com.example.emsApp.Entity.User;

import java.util.List;


public interface UserService {
    User getUser(Long id);

    User getUser(String username);

    User saveUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();
}
