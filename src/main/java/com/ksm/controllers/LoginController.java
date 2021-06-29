/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.controllers;

import com.ksm.models.LoginRequest;
import com.ksm.models.Register;
import com.ksm.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author WahyuKu
 */
@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String result = "";
        if(auth.getPrincipal().equals("anonimousUser")) {
            result =  "login";
        } else {
            result =  "redirect:/dashboard";
        }
        
        return result;
    }
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
    @PostMapping("/register")
    public String registerProcess(@RequestBody Register register) {
        //process service
        return "redirect:/login";
    }
    
    
    @PostMapping("/login")
    public String loginVerification(@RequestBody LoginRequest request) {
        String result = "";
        if (loginService.login(request)) {
            result = "/dashboard";
        } else {
            result = "?error";
        }
        
        return "redirtect:/login" + result;
    }
}
