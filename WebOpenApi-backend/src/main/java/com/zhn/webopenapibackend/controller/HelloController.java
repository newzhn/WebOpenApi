package com.zhn.webopenapibackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhn
 * @version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('user')")
    public String hello() {
        return "hello";
    }
}
