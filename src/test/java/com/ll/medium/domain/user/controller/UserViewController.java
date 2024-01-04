package com.ll.medium.domain.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String login() {return "login";}

    @GetMapping("/signup")
    @PreAuthorize("permitAll()")
    public String signup() {return "signup";}
}
