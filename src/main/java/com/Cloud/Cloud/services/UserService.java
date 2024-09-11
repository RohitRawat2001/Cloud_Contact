package com.Cloud.Cloud.services;


import java.util.List;
import java.util.Optional;

import com.Cloud.Cloud.entities.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String UserId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
    User getUserByEmail(String email);

    // add more methods here related to User Logic
}
