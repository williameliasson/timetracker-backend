package com.williameliasson.timetracker.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/example")
public class ExampleController {
    @GetMapping("")
    public String example() {
        return "Example";
    }

    @GetMapping("/authonly")
    public String getAuthOnly(Principal principal) {
        return "You are " + principal.getName();
    }
}
