package com.fcls.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("sys/employee/add")
    public String test1() {

        return "sys:employee:add";
    }

    @GetMapping("sys/employee/list")
    public String test2() {

        return "sys:employee:list";
    }
}
