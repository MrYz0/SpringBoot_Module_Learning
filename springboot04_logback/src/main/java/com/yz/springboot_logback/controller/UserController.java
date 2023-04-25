package com.yz.springboot_logback.controller;

import com.yz.springboot_logback.pojo.User;
import com.yz.springboot_logback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public List<User> getUsers(){

        List<User> list = userService.list();
        return list;
    }

}
