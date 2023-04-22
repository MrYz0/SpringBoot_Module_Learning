package com.yz.crossorigin.controller;

import com.yz.crossorigin.domain.User;
import com.yz.crossorigin.service.UserService;
import com.yz.crossorigin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public Result list(){
        List<User> list = userService.list();
        return Result.ok(list);
    }
}
