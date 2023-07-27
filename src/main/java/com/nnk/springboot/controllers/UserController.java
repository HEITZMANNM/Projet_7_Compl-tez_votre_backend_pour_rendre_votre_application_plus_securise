package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/poseidon")
public class UserController {


    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/user/list")
    public String home( Model model)
    {
        model.addAttribute("users", userRepository.findAll());

        Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for(GrantedAuthority grantedAuthority : grantedAuthorities)
        {
            if("ROLE_USER".equals(grantedAuthority.getAuthority()))
            {
                log.info("Access to page list of User was refused");
                return "redirect:/poseidon/bidList/list";
            }
        }
        log.info("Access to page list of User was done");
        return "user/list";
    }



    @GetMapping("/user/add")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        log.info("Acces to page to add new User was done");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            log.info("The new User was validated and saved");
            return "redirect:/poseidon/user/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "user/add";
        }

    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        log.info("The selected User was find and access to the page for update User was done");
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("Error to update the User");
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        log.info("The User was validated and updated");
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/poseidon/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        log.info("The selected User was deleted");
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/poseidon/user/list";
    }
}