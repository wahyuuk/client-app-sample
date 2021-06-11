/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.controllers;

import com.ksm.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author WahyuKu
 */
@Controller
@RequestMapping("person")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("persons", personService.getAll());
        return "person";
    }
    
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "detail";
    }
}
