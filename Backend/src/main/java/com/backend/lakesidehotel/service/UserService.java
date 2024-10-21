package com.backend.lakesidehotel.service;

import java.util.List;

import com.backend.lakesidehotel.model.User;

public interface UserService {
	User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);
//	User registerUserWithRole(User user, String roleName);
//	User registerUser(User user, String roleName);
}
