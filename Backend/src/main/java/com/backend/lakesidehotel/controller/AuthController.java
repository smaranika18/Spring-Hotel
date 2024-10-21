package com.backend.lakesidehotel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.lakesidehotel.exception.UserAlreadyExistsException;
import com.backend.lakesidehotel.model.Role;
import com.backend.lakesidehotel.model.User;
import com.backend.lakesidehotel.repository.RoleRepository;
import com.backend.lakesidehotel.request.LoginRequest;
import com.backend.lakesidehotel.response.JwtResponse;
import com.backend.lakesidehotel.security.jwt.JwtUtils;
import com.backend.lakesidehotel.security.user.HotelUserDetails;
import com.backend.lakesidehotel.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
        HotelUserDetails userDetails = (HotelUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                jwt,
                roles));
    }
    
    @PostMapping("/saverole")
    public void saveRole(@RequestBody Role role)
    {
    	
    	roleRepository.save(role);
    }
    
    
//    @GetMapping("/getrole")
//    public Role getRole()
//    {
//
//    		Role role = new Role();
//    		role.setId(1232322323l);
//    		role.setName("ROLE_USER");
//    		
//    		return role;
//    }
//    
//    
}


//package com.backend.lakesidehotel.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.backend.lakesidehotel.exception.UserAlreadyExistsException;
//import com.backend.lakesidehotel.model.Role;
//import com.backend.lakesidehotel.model.User;
//import com.backend.lakesidehotel.repository.RoleRepository;
//import com.backend.lakesidehotel.request.LoginRequest;
//import com.backend.lakesidehotel.response.JwtResponse;
//import com.backend.lakesidehotel.security.jwt.JwtUtils;
//import com.backend.lakesidehotel.security.user.HotelUserDetails;
//import com.backend.lakesidehotel.service.UserService;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//    private final UserService userService;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtils jwtUtils;
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @PostMapping("/register-user")
//    public ResponseEntity<?> registerUser(@RequestBody User user, @RequestParam("roleName") String roleName) {
//        try {
//            userService.registerUser(user, roleName);
//            return ResponseEntity.ok("Registration successful!");
//
//        } catch (UserAlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
//        Authentication authentication =
//                authenticationManager
//                        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
//        HotelUserDetails userDetails = (HotelUserDetails) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority).toList();
//        return ResponseEntity.ok(new JwtResponse(
//                userDetails.getId(),
//                userDetails.getEmail(),
//                jwt,
//                roles));
//    }
//
//    @PostMapping("/saverole")
//    public void saveRole(@RequestBody Role role) {
//        roleRepository.save(role);
//    }
//}
//
