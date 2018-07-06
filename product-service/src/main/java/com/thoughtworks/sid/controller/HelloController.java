package com.thoughtworks.sid.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }
}
