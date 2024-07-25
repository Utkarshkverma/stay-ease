package com.vermau2k01.stay_ease.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home")
    public String adminHome() {
        return "adminHome";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/manager/home")
    public String managerHome() {
        return "managerHome";
    }
}
