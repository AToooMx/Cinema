package com.clevercinema.controllers;

import com.clevercinema.entity.Authorities;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyRestController {
    @GetMapping("/roles")
    public String getRoles(Authentication authentication) {
        if (authentication != null) {
            return authentication.getAuthorities().toString();
        } else {
            return "";
        }
    }
}
