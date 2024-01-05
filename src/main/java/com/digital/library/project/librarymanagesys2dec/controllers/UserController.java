package com.digital.library.project.librarymanagesys2dec.controllers;

import com.digital.library.project.librarymanagesys2dec.createRequest.UserCreateRequest;
import com.digital.library.project.librarymanagesys2dec.security.User;
import com.digital.library.project.librarymanagesys2dec.security.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("/newUser")
    public void createUser(@Valid @RequestBody UserCreateRequest user){
        userService.saveUser(user);
    }
}
