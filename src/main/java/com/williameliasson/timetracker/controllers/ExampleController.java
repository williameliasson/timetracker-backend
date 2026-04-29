package com.williameliasson.timetracker.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/example")
public class ExampleController {
    @GetMapping("")
    public String example() {
        return "Example";
    }
}
