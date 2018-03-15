package com.szmengran.security.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
    
    @GetMapping("/users")
    public String user(){
        return "limaoyuan";
    }
    
    @GetMapping("/api/users")
    public Principal users(){
    	return null;
    }
}
