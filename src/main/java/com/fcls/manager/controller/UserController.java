package com.fcls.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("user")
public class UserController {
    @GetMapping("tt")
    public String userin(){
        return "lasdkfj";

    }
}
