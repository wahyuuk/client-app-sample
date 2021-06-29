/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.services;

import com.ksm.models.LoginRequest;
import com.ksm.models.LoginResponse;
import com.ksm.models.Person;
import com.ksm.models.Register;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author WahyuKu
 */
@Service
public class LoginService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${base.url}/login")
    private String URL;
    
    public boolean login(LoginRequest request) {
        try {
            ResponseEntity<LoginResponse> response = 
                    restTemplate.postForEntity(URL, request, LoginResponse.class);
            
            
            return true;
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return false;
            }
        }
        
        return false;
    }
    
    public boolean register(Register register) {
        //process save
    }
    
    public void setSession(LoginRequest request, LoginResponse response) {
        UsernamePasswordAuthenticationToken token = 
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(), 
                        authorities(response.getRoles()));
    }
    
    public List<GrantedAuthority> authorities(List<String> roles) {
        List<GrantedAuthority> auths = new ArrayList<>();
        
        for (String role : roles) {
            auths.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
        }
        
        return auths;
    }
}
