//package com.backend.lakesidehotel.service;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.backend.lakesidehotel.exception.UserAlreadyExistsException;
//import com.backend.lakesidehotel.model.Role;
//import com.backend.lakesidehotel.model.User;
//import com.backend.lakesidehotel.repository.RoleRepository;
//import com.backend.lakesidehotel.repository.UserRepository;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//
//    @Override
//    public User registerUser(User user) {
//        return registerUserWithRole(user, "ROLE_USER");
//    }
//
//    @Override
//    public User registerUserWithRole(User user, String roleName) {
//        if (userRepository.existsByEmail(user.getEmail())) {
//            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        System.out.println(user.getPassword());
//
//        Role role = roleRepository.findByName(roleName)
//                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
//        user.setRoles(Collections.singletonList(role));
//        return userRepository.save(user);
//    }
//
//    @Override
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    @Transactional
//    @Override
//    public void deleteUser(String email) {
//        User theUser = getUser(email);
//        if (theUser != null) {
//            userRepository.deleteByEmail(email);
//        }
//    }
//
//    @Override
//    public User getUser(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//}



package com.backend.lakesidehotel.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.lakesidehotel.exception.UserAlreadyExistsException;
import com.backend.lakesidehotel.model.Role;
import com.backend.lakesidehotel.model.User;
import com.backend.lakesidehotel.repository.RoleRepository;
import com.backend.lakesidehotel.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

//    @Override
//    public User registerUser(User user) {
//    	
////    	if password not included:
//        return registerUserWithRole(user, "ROLE_ADMIN");
//        
////        if admin password included:
////        return registerUserWithRole(user, "ROLE_ADMIN");
////        
////        if editor password included:
////        return registerUserWithRole(user, "ROLE_EDITOR");
//    }
//
//    @Override
//    public User registerUserWithRole(User user, String roleName) {
//        if (userRepository.existsByEmail(user.getEmail())) {
//            throw new UserAlreadyExistsException(user.getEmail() + " Already Exists");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        System.out.println(user.getPassword());
//
//        Role role = roleRepository.findByName(roleName)
//                .orElseThrow(() -> new RuntimeException("Role Not Found: " + roleName));
//        user.setRoles(Collections.singletonList(role));
//        return userRepository.save(user);
//    }
    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null) {
            userRepository.deleteByEmail(email);
        }
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }



}
